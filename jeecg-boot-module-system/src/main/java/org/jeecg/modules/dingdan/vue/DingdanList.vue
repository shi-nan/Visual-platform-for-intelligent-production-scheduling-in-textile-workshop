<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->
    
    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('订单表')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <!-- 高级查询区域 -->
      <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>
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
        class="j-table-force-nowrap"
        :scroll="{x:true}"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
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

    <dingdan-modal ref="modalForm" @ok="modalFormOk"/>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import DingdanModal from './modules/DingdanModal'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'
  import '@/assets/less/TableExpand.less'
  import JSuperQuery from '@/components/jeecg/JSuperQuery.vue'

  export default {
    name: "DingdanList",
    mixins:[JeecgListMixin],
    components: {
      DingdanModal,
      JSuperQuery
    },
    data () {
      return {
        description: '订单表管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'订单号',
            align:"center",
            dataIndex: 'orderId'
          },
          {
            title:'订单日期',
            align:"center",
            dataIndex: 'orderDate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'订单客户',
            align:"center",
            dataIndex: 'orderCustomer'
          },
          {
            title:'预发货日期',
            align:"center",
            dataIndex: 'advanceShipmentdate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'BOM状态',
            align:"center",
            dataIndex: 'bomStatus'
          },
          {
            title:'是否生单',
            align:"center",
            dataIndex: 'ynOrder_dictText'
          },
          {
            title:'是否确认',
            align:"center",
            dataIndex: 'ynEnsure_dictText'
          },
          {
            title:'设置约束',
            align:"center",
            dataIndex: 'setBind_dictText'
          },
          {
            title:'优先级',
            align:"center",
            dataIndex: 'precedence'
          },
          {
            title:'订单状态',
            align:"center",
            dataIndex: 'orderStatus_dictText'
          },
          {
            title:'排单状态',
            align:"center",
            dataIndex: 'scheduleStatus_dictText'
          },
          {
            title:'生产状态',
            align:"center",
            dataIndex: 'productionStatus_dictText'
          },
          {
            title:'排单员',
            align:"center",
            dataIndex: 'scheduleStaff'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' },
          }
        ],
        url: {
          list: "/dingdan/dingdan/list",
          delete: "/dingdan/dingdan/delete",
          deleteBatch: "/dingdan/dingdan/deleteBatch",
          exportXlsUrl: "/dingdan/dingdan/exportXls",
          importExcelUrl: "dingdan/dingdan/importExcel",
          
        },
        dictOptions:{},
        superFieldList:[],
      }
    },
    created() {
      this.getSuperFieldList();
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      }
    },
    methods: {
      initDictConfig(){
      },
      getSuperFieldList(){
        let fieldList=[];
         fieldList.push({type:'string',value:'orderId',text:'订单号',dictCode:''})
         fieldList.push({type:'date',value:'orderDate',text:'订单日期'})
         fieldList.push({type:'string',value:'orderCustomer',text:'订单客户',dictCode:''})
         fieldList.push({type:'date',value:'advanceShipmentdate',text:'预发货日期'})
         fieldList.push({type:'string',value:'bomStatus',text:'BOM状态',dictCode:''})
         fieldList.push({type:'string',value:'ynOrder',text:'是否生单',dictCode:'yes_no'})
         fieldList.push({type:'string',value:'ynEnsure',text:'是否确认',dictCode:'yes_no'})
         fieldList.push({type:'list_multi',value:'setBind',text:'设置约束',dictTable:'', dictText:'', dictCode:'schedulebind'})
         fieldList.push({type:'string',value:'precedence',text:'优先级',dictCode:''})
         fieldList.push({type:'string',value:'orderStatus',text:'订单状态',dictCode:'dingdanzhuangtai'})
         fieldList.push({type:'string',value:'scheduleStatus',text:'排单状态',dictCode:'paidanzhuangtai'})
         fieldList.push({type:'string',value:'productionStatus',text:'生产状态',dictCode:'shengchanzhuangtai'})
         fieldList.push({type:'string',value:'scheduleStaff',text:'排单员',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>