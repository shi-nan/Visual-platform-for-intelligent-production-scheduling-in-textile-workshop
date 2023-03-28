<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-item label="创建日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择创建日期" v-decorator="['createTime', validatorRules.createTime]" :trigger-change="true" style="width: 100%" />
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="订单号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['dingdanId', validatorRules.dingdanId]" placeholder="请输入订单号"  ></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="粗纱" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['cusha', validatorRules.cusha]" placeholder="请输入粗纱" style="width: 100%" />
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="细纱" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['xisha', validatorRules.xisha]" placeholder="请输入细纱" style="width: 100%" />
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="混条" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['huntiao', validatorRules.huntiao]" placeholder="请输入混条" style="width: 100%" />
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="头针" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['touzhen', validatorRules.touzhen]" placeholder="请输入头针" style="width: 100%" />
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="二针" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['erzhen', validatorRules.erzhen]" placeholder="请输入二针" style="width: 100%" />
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="三针" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['sanzhen', validatorRules.sanzhen]" placeholder="请输入三针" style="width: 100%" />
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="四针" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['sizhen', validatorRules.sizhen]" placeholder="请输入四针" style="width: 100%" />
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="五针" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['wuzhen', validatorRules.wuzhen]" placeholder="请输入五针" style="width: 100%" />
            </a-form-item>
          </a-col>
          <a-col v-if="showFlowSubmitButton" :span="24" style="text-align: center">
            <a-button @click="submitForm">提 交</a-button>
          </a-col>
        </a-row>
      </a-form>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JFormContainer from '@/components/jeecg/JFormContainer'
  import JDate from '@/components/jeecg/JDate'  

  export default {
    name: 'DingdanjinchengForm',
    components: {
      JFormContainer,
      JDate,
    },
    props: {
      //流程表单data
      formData: {
        type: Object,
        default: ()=>{},
        required: false
      },
      //表单模式：true流程表单 false普通表单
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
    data () {
      return {
        form: this.$form.createForm(this),
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
          createTime: {
            rules: [
              { required: true, message: '请输入创建日期!'},
            ]
          },
          dingdanId: {
            rules: [
              { required: true, message: '请输入订单号!'},
            ]
          },
          cusha: {
            rules: [
              { required: true, message: '请输入粗纱!'},
            ]
          },
          xisha: {
            rules: [
              { required: true, message: '请输入细纱!'},
            ]
          },
          huntiao: {
            rules: [
              { required: true, message: '请输入混条!'},
            ]
          },
          touzhen: {
            rules: [
              { required: true, message: '请输入头针!'},
            ]
          },
          erzhen: {
            rules: [
              { required: true, message: '请输入二针!'},
            ]
          },
          sanzhen: {
            rules: [
              { required: true, message: '请输入三针!'},
            ]
          },
          sizhen: {
            rules: [
              { required: true, message: '请输入四针!'},
            ]
          },
          wuzhen: {
            rules: [
              { required: true, message: '请输入五针!'},
            ]
          },
        },
        url: {
          add: "/dingdanjincheng/dingdanjincheng/add",
          edit: "/dingdanjincheng/dingdanjincheng/edit",
          queryById: "/dingdanjincheng/dingdanjincheng/queryById"
        }
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
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'createTime','dingdanId','cusha','xisha','huntiao','touzhen','erzhen','sanzhen','sizhen','wuzhen'))
        })
      },
      //渲染流程表单数据
      showFlowData(){
        if(this.formBpm === true){
          let params = {id:this.formData.dataId};
          getAction(this.url.queryById,params).then((res)=>{
            if(res.success){
              this.edit (res.result);
            }
          });
        }
      },
      submitForm () {
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
            })
          }
         
        })
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'createTime','dingdanId','cusha','xisha','huntiao','touzhen','erzhen','sanzhen','sizhen','wuzhen'))
      },
    }
  }
</script>