<template>
  <a-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="所属部门" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <j-select-depart placeholder="请输入所属部门" v-decorator="['sysOrgCode',validatorRules.sysOrgCode]" :trigger-change="true" ></j-select-depart>
        </a-form-item>
        <a-form-item label="小区名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'name', validatorRules.name]" placeholder="请输入小区名称"></a-input>
        </a-form-item>
        <a-form-item label="行政区编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'areaCode', validatorRules.areaCode]" placeholder="请输入行政区编码"></a-input>
        </a-form-item>
        <a-form-item label="纬度" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'latitude', validatorRules.latitude]" placeholder="请输入纬度"></a-input>
        </a-form-item>
        <a-form-item label="经度" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'longitude', validatorRules.longitude]" placeholder="请输入经度"></a-input>
        </a-form-item>
        <a-form-item label="楼栋数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'buildingsNumber', validatorRules.buildingsNumber]" placeholder="请输入楼栋数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="单元数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'cellNumber', validatorRules.cellNumber]" placeholder="请输入单元数量" style="width: 100%"/>
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JSelectDepart from '@/components/jeecgbiz/JSelectDepart'

  export default {
    name: "VillageModal",
    components: {
      JSelectDepart
    },
    data () {
      return {
        form: this.$form.createForm(this),
        title:"操作",
        width:800,
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
          sysOrgCode: {rules: [
            {required: true, message: '请输入所属部门!'},
          ]},
          name: {rules: [
            {required: true, message: '请输入小区名称!'},
            { validator: (rule, value, callback) => validateDuplicateValue('cm_village', 'name', value, this.model.id, callback)},
          ]},
          areaCode: {rules: [
          ]},
          latitude: {rules: [
            {required: true, message: '请输入纬度!'},
          ]},
          longitude: {rules: [
            {required: true, message: '请输入经度!'},
          ]},
          buildingsNumber: {rules: [
            {required: true, message: '请输入楼栋数量!'},
          ]},
          cellNumber: {rules: [
          ]},
        },
        url: {
          add: "/iot/village/add",
          edit: "/iot/village/edit",
        }
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'createTime','sysOrgCode','name','areaCode','latitude','longitude','buildingsNumber','cellNumber'))
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }

        })
      },
      handleCancel () {
        this.close()
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'createTime','sysOrgCode','name','areaCode','latitude','longitude','buildingsNumber','cellNumber'))
      },


    }
  }
</script>
