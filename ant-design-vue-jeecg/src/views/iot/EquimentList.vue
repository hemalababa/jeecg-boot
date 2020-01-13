<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">

            <a-col :md="6" :sm="8">
                <a-form-item label="设备类型">
                    <j-dict-select-tag v-model="queryParam.type" title="设备类型" dictCode="equipment_type"/>
                </a-form-item>
            </a-col>

            <a-col :md="6" :sm="8">
                <a-form-item label="设备进出类型">
                    <j-dict-select-tag v-model="queryParam.ieType" title="设备进出类型" dictCode="equipment_ie_type"/>
                </a-form-item>
            </a-col>
            <a-col :md="6" :sm="8">
                <a-form-item label="摄像头分布">
                    <j-dict-select-tag v-model="queryParam.cameraDist" title="摄像头分布" dictCode="equipment_camera_dist"/>
                </a-form-item>
            </a-col>
        <template v-if="toggleSearchStatus">

            <a-col :md="6" :sm="8">
                <a-form-item label="name">
                    <a-input placeholder="请输入name" v-model="queryParam.name"></a-input>
                </a-form-item>
            </a-col>
        <a-col :md="6" :sm="8">
            <a-form-item label="ip">
              <a-input placeholder="请输入ip" v-model="queryParam.ip"></a-input>
            </a-form-item>
          </a-col>

          </template>
          <a-col :md="6" :sm="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('设备表')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>
    <!-- table区域-end -->

    <!-- 表单区域 -->
    <equiment-modal ref="modalForm" @ok="modalFormOk"></equiment-modal>
  </a-card>
</template>

<script>
  import EquimentModal from './modules/EquimentModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import JDictSelectTag from '@/components/dict/JDictSelectTag'

  export default {
    name: "EquimentList",
    mixins:[JeecgListMixin],
    components: {
      EquimentModal,
      JDictSelectTag
    },
    data () {
      return {
        description: '设备表管理页面',
        // 表头
        columns: [
		   {
            title: '设备名称',
            align:"center",
            dataIndex: 'name'
           },
          {
            title: '安装位置',
            align:"center",
            dataIndex: 'position'
          },
          {
            title: 'ip',
            align:"center",
            dataIndex: 'ip'
          },
		   {
            title: '设备类型',
            align:"center",
            dataIndex: 'type_dictText'
           },
		   {
            title: '设备进出类型',
            align:"center",
            dataIndex: 'ieType_dictText'
           },
		   {
            title: '摄像头分布',
            align:"center",
            dataIndex: 'cameraDist_dictText'
           },
          {
            title: '视频流地址',
            align:"center",
            dataIndex: 'streamUrl'
          },
          {
            title: '安装时间',
            align:"center",
            dataIndex: 'installTime'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/iot/equiment/list",
          delete: "/iot/equiment/delete",
          deleteBatch: "/iot/equiment/deleteBatch",
          exportXlsUrl: "iot/equiment/exportXls",
          importExcelUrl: "iot/equiment/importExcel",
       },
    }
  },
  computed: {
    importExcelUrl: function(){
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
    }
  },
    methods: {

    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>
