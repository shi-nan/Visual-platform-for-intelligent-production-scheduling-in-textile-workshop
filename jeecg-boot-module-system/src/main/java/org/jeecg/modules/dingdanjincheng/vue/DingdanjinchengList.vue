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
      <a-button type="primary" icon="download" @click="handleExportXls('记录订单进程')">导出</a-button>
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
        :scroll="{x:true}"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        class="j-table-force-nowrap"
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

    <dingdanjincheng-modal ref="modalForm" @ok="modalFormOk"></dingdanjincheng-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import DingdanjinchengModal from './modules/DingdanjinchengModal'
  import JSuperQuery from '@/components/jeecg/JSuperQuery.vue'

  export default {
    name: 'DingdanjinchengList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      DingdanjinchengModal,
      JSuperQuery,
    },
    data () {
      return {
        description: '记录订单进程管理页面',
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
            title:'创建日期',
            align:"center",
            dataIndex: 'createTime',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'订单号',
            align:"center",
            dataIndex: 'dingdanId'
          },
          {
            title:'粗纱',
            align:"center",
            dataIndex: 'cusha'
          },
          {
            title:'细纱',
            align:"center",
            dataIndex: 'xisha'
          },
          {
            title:'混条',
            align:"center",
            dataIndex: 'huntiao'
          },
          {
            title:'头针',
            align:"center",
            dataIndex: 'touzhen'
          },
          {
            title:'二针',
            align:"center",
            dataIndex: 'erzhen'
          },
          {
            title:'三针',
            align:"center",
            dataIndex: 'sanzhen'
          },
          {
            title:'四针',
            align:"center",
            dataIndex: 'sizhen'
          },
          {
            title:'五针',
            align:"center",
            dataIndex: 'wuzhen'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/dingdanjincheng/dingdanjincheng/list",
          delete: "/dingdanjincheng/dingdanjincheng/delete",
          deleteBatch: "/dingdanjincheng/dingdanjincheng/deleteBatch",
          exportXlsUrl: "/dingdanjincheng/dingdanjincheng/exportXls",
          importExcelUrl: "dingdanjincheng/dingdanjincheng/importExcel",
          
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
      },
    },
    methods: {
      initDictConfig(){
      },
      getSuperFieldList(){
        let fieldList=[];
        fieldList.push({type:'date',value:'createTime',text:'创建日期'})
        fieldList.push({type:'string',value:'dingdanId',text:'订单号',dictCode:''})
        fieldList.push({type:'int',value:'cusha',text:'粗纱',dictCode:''})
        fieldList.push({type:'int',value:'xisha',text:'细纱',dictCode:''})
        fieldList.push({type:'int',value:'huntiao',text:'混条',dictCode:''})
        fieldList.push({type:'int',value:'touzhen',text:'头针',dictCode:''})
        fieldList.push({type:'int',value:'erzhen',text:'二针',dictCode:''})
        fieldList.push({type:'int',value:'sanzhen',text:'三针',dictCode:''})
        fieldList.push({type:'int',value:'sizhen',text:'四针',dictCode:''})
        fieldList.push({type:'int',value:'wuzhen',text:'五针',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>