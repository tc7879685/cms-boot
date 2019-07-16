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
          label="traceUUID">
          <a-input placeholder="请输入traceUUID" v-decorator="['traceUUID', validatorRules.traceUUID ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="corpCode">
          <a-input placeholder="请输入corpCode" v-decorator="['corpCode', validatorRules.corpCode ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="userCode">
          <a-input placeholder="请输入userCode" v-decorator="['userCode', validatorRules.userCode ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="menuCode">
          <a-input placeholder="请输入menuCode" v-decorator="['menuCode', validatorRules.menuCode ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="operateTime">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'operateTime', validatorRules.operateTime ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="operateType">
          <a-input-number v-decorator="[ 'operateType', validatorRules.operateType ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="operateInfo">
          <a-input placeholder="请输入operateInfo" v-decorator="['operateInfo', {}]" />
        </a-form-item>
		
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from "moment"

  export default {
    name: "TraceInfoModal",
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
        traceUUID:{rules: [{ required: true, message: '请输入traceUUID!' }]},
        corpCode:{rules: [{ required: true, message: '请输入corpCode!' }]},
        userCode:{rules: [{ required: true, message: '请输入userCode!' }]},
        menuCode:{rules: [{ required: true, message: '请输入menuCode!' }]},
        operateTime:{rules: [{ required: true, message: '请输入operateTime!' }]},
        operateType:{rules: [{ required: true, message: '请输入operateType!' }]},
        },
        url: {
          add: "/system/traceInfo/add",
          edit: "/system/traceInfo/edit",
        },
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
          this.form.setFieldsValue(pick(this.model,'traceUUID','corpCode','userCode','menuCode','operateType','operateInfo'))
		  //时间格式化
          this.form.setFieldsValue({operateTime:this.model.operateTime?moment(this.model.operateTime):null})
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
            formData.operateTime = formData.operateTime?formData.operateTime.format('YYYY-MM-DD HH:mm:ss'):null;
            
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


    }
  }
</script>

<style lang="less" scoped>

</style>