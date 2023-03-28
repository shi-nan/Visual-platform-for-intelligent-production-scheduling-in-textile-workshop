<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <!-- 主表单区域 -->
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="12" >
            <a-form-item label="订单号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['orderId', validatorRules.orderId]" placeholder="请输入订单号" ></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12" >
            <a-form-item label="订单日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择订单日期" v-decorator="['orderDate', validatorRules.orderDate]" :trigger-change="true" style="width: 100%" />
            </a-form-item>
          </a-col>
          <a-col :span="12" >
            <a-form-item label="订单客户" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['orderCustomer', validatorRules.orderCustomer]" placeholder="请输入订单客户" ></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12" >
            <a-form-item label="预发货日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择预发货日期" v-decorator="['advanceShipmentdate']" :trigger-change="true" style="width: 100%" />
            </a-form-item>
          </a-col>
          <a-col :span="12" >
            <a-form-item label="BOM状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['bomStatus']" placeholder="请输入BOM状态" ></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12" >
            <a-form-item label="是否生单" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag type="list" v-decorator="['ynOrder', validatorRules.ynOrder]" :trigger-change="true" dictCode="yes_no" placeholder="请选择是否生单" />
            </a-form-item>
          </a-col>
          <a-col :span="12" >
            <a-form-item label="是否确认" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag type="list" v-decorator="['ynEnsure', validatorRules.ynEnsure]" :trigger-change="true" dictCode="yes_no" placeholder="请选择是否确认" />
            </a-form-item>
          </a-col>
          <a-col :span="12" >
            <a-form-item label="设置约束" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-multi-select-tag type="list_multi" v-decorator="['setBind']" :trigger-change="true" dictCode="schedulebind" placeholder="请选择设置约束" />
            </a-form-item>
          </a-col>
          <a-col :span="12" >
            <a-form-item label="优先级" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['precedence']" placeholder="请输入优先级" ></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12" >
            <a-form-item label="订单状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag type="list" v-decorator="['orderStatus', validatorRules.orderStatus]" :trigger-change="true" dictCode="dingdanzhuangtai" placeholder="请选择订单状态" />
            </a-form-item>
          </a-col>
          <a-col :span="12" >
            <a-form-item label="排单状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag type="list" v-decorator="['scheduleStatus', validatorRules.scheduleStatus]" :trigger-change="true" dictCode="paidanzhuangtai" placeholder="请选择排单状态" />
            </a-form-item>
          </a-col>
          <a-col :span="12" >
            <a-form-item label="生产状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag type="list" v-decorator="['productionStatus', validatorRules.productionStatus]" :trigger-change="true" dictCode="shengchanzhuangtai" placeholder="请选择生产状态" />
            </a-form-item>
          </a-col>
          <a-col :span="12" >
            <a-form-item label="排单员" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['scheduleStaff']" placeholder="请输入排单员" ></a-input>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>
    </j-form-container>
      <!-- 子表单区域 -->
    <a-tabs v-model="activeKey" @change="handleChangeTabs">
      <a-tab-pane tab="订单详情" :key="refKeys[0]" :forceRender="true">
        <j-editable-table
          :ref="refKeys[0]"
          :loading="dingdanxiangqingTable.loading"
          :columns="dingdanxiangqingTable.columns"
          :dataSource="dingdanxiangqingTable.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :actionButton="true"/>
      </a-tab-pane>
      <a-tab-pane tab="工人换班" :key="refKeys[1]" :forceRender="true">
        <j-editable-table
          :ref="refKeys[1]"
          :loading="staffchangeTable.loading"
          :columns="staffchangeTable.columns"
          :dataSource="staffchangeTable.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :actionButton="true"/>
      </a-tab-pane>
    </a-tabs>
  </a-spin>
</template>

<script>

  import pick from 'lodash.pick'
  import { getAction } from '@/api/manage'
  import { FormTypes,getRefPromise } from '@/utils/JEditableTableUtil'
  import { JEditableTableMixin } from '@/mixins/JEditableTableMixin'
  import { validateDuplicateValue } from '@/utils/util'
  import JFormContainer from '@/components/jeecg/JFormContainer'
  import JDate from '@/components/jeecg/JDate'  
  import JDictSelectTag from "@/components/dict/JDictSelectTag"
  import JMultiSelectTag from "@/components/dict/JMultiSelectTag"

  export default {
    name: 'DingdanForm',
    mixins: [JEditableTableMixin],
    components: {
      JFormContainer,
      JDate,
      JDictSelectTag,
      JMultiSelectTag,
    },
    data() {
      return {
        labelCol: {
          xs: { span: 24 },
          sm: { span: 6 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        labelCol2: {
          xs: { span: 24 },
          sm: { span: 3 },
        },
        wrapperCol2: {
          xs: { span: 24 },
          sm: { span: 20 },
        },
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
          orderId: {
            rules: [
              { required: true, message: '请输入订单号!'},
            ]
          },
          orderDate: {
            rules: [
              { required: true, message: '请输入订单日期!'},
            ]
          },
          orderCustomer: {
            rules: [
              { required: true, message: '请输入订单客户!'},
            ]
          },
          ynOrder: {
            rules: [
              { required: true, message: '请输入是否生单!'},
            ]
          },
          ynEnsure: {
            rules: [
              { required: true, message: '请输入是否确认!'},
            ]
          },
          orderStatus: {
            initialValue:"0",
            rules: [
              { required: true, message: '请输入订单状态!'},
            ]
          },
          scheduleStatus: {
            initialValue:"0",
            rules: [
              { required: true, message: '请输入排单状态!'},
            ]
          },
          productionStatus: {
            initialValue:"0",
            rules: [
              { required: true, message: '请输入生产状态!'},
            ]
          },
        },
        refKeys: ['dingdanxiangqing', 'staffchange', ],
        tableKeys:['dingdanxiangqing', 'staffchange', ],
        activeKey: 'dingdanxiangqing',
        // 订单详情
        dingdanxiangqingTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '纱线名称',
              key: 'shanxianName',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '纱线种类',
              key: 'shaxianElement',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '纱线织数',
              key: 'shaxianZhishu',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '纱线颜色',
              key: 'shaxianColor',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '需求量',
              key: 'orderWeight',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '价格',
              key: 'money',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '订单号',
              key: 'orderId',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '完成数量',
              key: 'completeNumber',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
          ]
        },
        // 工人换班
        staffchangeTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '员工ID',
              key: 'staffId',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '早班时间',
              key: 'dayShift',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '午班时间',
              key: 'nightShift',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '订单编号',
              key: 'orderId',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '加班时间',
              key: 'prolongTime',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
          ]
        },
        url: {
          add: "/dingdan/dingdan/add",
          edit: "/dingdan/dingdan/edit",
          queryById: "/dingdan/dingdan/queryById",
          dingdanxiangqing: {
            list: '/dingdan/dingdan/queryDingdanxiangqingByMainId'
          },
          staffchange: {
            list: '/dingdan/dingdan/queryStaffchangeByMainId'
          },
        }
      }
    },
    props: {
      //流程表单data
      formData: {
        type: Object,
        default: ()=>{},
        required: false
      },
      //表单模式：false流程表单 true普通表单
      formBpm: {
        type: Boolean,
        default: false,
        required: false
      },
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    computed: {
      formDisabled(){
        if(this.formBpm===true){
          if(this.formData.disabled===false){
            return false
          }
          return true
        }
        return this.disabled
      },
      showFlowSubmitButton(){
        if(this.formBpm===true){
          if(this.formData.disabled===false){
            return true
          }
        }
        return false
      }
    },
    created () {
      //如果是流程中表单，则需要加载流程表单data
      this.showFlowData();
    },
    methods: {
      addBefore(){
        this.form.resetFields()
        this.dingdanxiangqingTable.dataSource=[]
        this.staffchangeTable.dataSource=[]
      },
      getAllTable() {
        let values = this.tableKeys.map(key => getRefPromise(this, key))
        return Promise.all(values)
      },
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        let fieldval = pick(this.model,'orderId','orderDate','orderCustomer','advanceShipmentdate','bomStatus','ynOrder','ynEnsure','setBind','precedence','orderStatus','scheduleStatus','productionStatus','scheduleStaff')
        this.$nextTick(() => {
          this.form.setFieldsValue(fieldval)
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.dingdanxiangqing.list, params, this.dingdanxiangqingTable)
          this.requestSubTableData(this.url.staffchange.list, params, this.staffchangeTable)
        }
      },
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        return {
          ...main, // 展开
          dingdanxiangqingList: allValues.tablesValue[0].values,
          staffchangeList: allValues.tablesValue[1].values,
        }
      },
      //渲染流程表单数据
      showFlowData(){
        if(this.formBpm === true){
          let params = {id:this.formData.dataId};
          getAction(this.url.queryById,params).then((res)=>{
            if(res.success){
              this.edit (res.result);
            }
          })
        }
      },
      validateError(msg){
        this.$message.error(msg)
      },
     popupCallback(row){
       this.form.setFieldsValue(pick(row,'orderId','orderDate','orderCustomer','advanceShipmentdate','bomStatus','ynOrder','ynEnsure','setBind','precedence','orderStatus','scheduleStatus','productionStatus','scheduleStaff'))
     },

    }
  }
</script>

<style scoped>
</style>