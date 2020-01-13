<template>
  <a-modal
    :title="title"
    :width="800"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">

    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

          <a-form-item
                  :labelCol="labelCol"
                  :wrapperCol="wrapperCol"
                  label="设备名称">
              <a-input placeholder="请输入设备名称" v-decorator="['name', {}]" />
          </a-form-item>
          <a-form-item
                  :labelCol="labelCol"
                  :wrapperCol="wrapperCol"
                  label="安装位置">
              <a-input placeholder="请输入安装位置" v-decorator="['position', {}]" />
          </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="经度lon">
          <a-input placeholder="请输入lon" v-decorator="['lon', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="纬度lat">
          <a-input placeholder="请输入lat" v-decorator="['lat', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="ip地址">
          <a-input placeholder="请输入ip" v-decorator="['ip', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="mac地址">
          <a-input placeholder="请输入mac" v-decorator="['mac', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="设备类型">
            <j-dict-select-tag  v-decorator="['type', {}]" :triggerChange="true" placeholder="请选择设备类型"
                                dictCode="equipment_type"/>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="设备进出类型">
            <j-dict-select-tag  v-decorator="['ieType', {}]" :triggerChange="true" placeholder="请选择进出类型"
                                dictCode="equipment_ie_type"/>
        </a-form-item>
          <a-form-item
                  :labelCol="labelCol"
                  :wrapperCol="wrapperCol"
                  label="车辆进出类型">
              <j-dict-select-tag  v-decorator="['carType', {}]" :triggerChange="true" placeholder="请选择进出类型"
                                  dictCode="equipment_ie_type"/>
          </a-form-item>

          <a-form-item
                  :labelCol="labelCol"
                  :wrapperCol="wrapperCol"
                  label="摄像头分布">
              <j-dict-select-tag  v-decorator="['cameraDist', {}]" :triggerChange="true" placeholder="请选择类型"
                                  dictCode="equipment_camera_dist"/>
          </a-form-item>

          <a-form-item
                  :labelCol="labelCol"
                  :wrapperCol="wrapperCol"
                  label="streamUrl">
              <a-input placeholder="请输入视频流地址" v-decorator="['streamUrl', {}]" />
          </a-form-item>
          <a-form-item
                  :labelCol="labelCol"
                  :wrapperCol="wrapperCol"
                  label="设备安装高度">
              <a-input placeholder="请输入height" v-decorator="['height', {}]" />
          </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="小区编码">
            <a-select  placeholder="请输入小区编码"   v-decorator="['villageId', {}]" >
                <a-select-option value="">请选择</a-select-option>
                <a-select-option v-for="(item, key) in villageOptions" :key="key" :value="item.id">
      <span style="display: inline-block;width: 100%" :title="item.name">
        {{ item.name}}
      </span>
                </a-select-option>
            </a-select>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="人脸平台unionId">
          <a-input placeholder="请输入unionId" v-decorator="['unionId', {}]" />
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from "moment"
  import JDictSelectTag from '@/components/dict/JDictSelectTag'

  export default {
    name: "EquimentModal",
    components: {
      JDictSelectTag
    },
    data () {
      return {
        title:"操作",
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
        form: this.$form.createForm(this),
        validatorRules:{
        },
        url: {
          add: "/iot/equiment/add",
          edit: "/iot/equiment/edit",
          getVillageList:"/iot/village/getVillageList"
        },
        villageOptions:[],
      }
    },
    created () {
      //加载当前用户管理的所有小区
      this.getVillageList();
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
          this.form.setFieldsValue(pick(this.model,'lon','lat','ip','mac','name','type','ieType','villageId','installTime','directionAngle','coverage','status','checkTime','offlineTime','streamUrl','buildingId','rssi','position','channelId','eqOrder','unionId','showFlag','isCollect','carType','height','cameraDist'))
		  //时间格式化
        });

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
            //时间格式化

            console.log(formData)
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
      getVillageList(){
        httpAction(this.url.getVillageList,"get").then((res)=>{
          if(res.success){
            this.villageOptions = res.result;
          }else{
            this.$message.warning(res.message);
          }
        }).finally(() => {
          this.close();
        })
      }

    }
  }
</script>

<style lang="less" scoped>

</style>
