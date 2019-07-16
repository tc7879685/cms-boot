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
          label="busiUUID">
          <a-input-number v-decorator="[ 'busiUUID', validatorRules.busiUUID ]" />
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
          label="busiType">
          <a-input-number v-decorator="[ 'busiType', validatorRules.busiType ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="busiCode">
          <a-input placeholder="请输入busiCode" v-decorator="['busiCode', validatorRules.busiCode ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="busiName">
          <a-input placeholder="请输入busiName" v-decorator="['busiName', validatorRules.busiName ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="parentCode">
          <a-input placeholder="请输入parentCode" v-decorator="['parentCode', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="busiMemo">
          <a-input placeholder="请输入busiMemo" v-decorator="['busiMemo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="showIndex">
          <a-input-number v-decorator="[ 'showIndex', validatorRules.showIndex ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="paymentFlag">
          <a-input-number v-decorator="[ 'paymentFlag', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="holdFlag">
          <a-input-number v-decorator="[ 'holdFlag', validatorRules.holdFlag ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="statusCode">
          <a-input-number v-decorator="[ 'statusCode', validatorRules.statusCode ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="showFlag">
          <a-input-number v-decorator="[ 'showFlag', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="flowFlag">
          <a-input-number v-decorator="[ 'flowFlag', {}]" />
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
    name: "BusiInfoModal",
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
        busiUUID:{rules: [{ required: true, message: '请输入busiUUID!' }]},
        corpCode:{rules: [{ required: true, message: '请输入corpCode!' }]},
        busiType:{rules: [{ required: true, message: '请输入busiType!' }]},
        busiCode:{rules: [{ required: true, message: '请输入busiCode!' }]},
        busiName:{rules: [{ required: true, message: '请输入busiName!' }]},
        showIndex:{rules: [{ required: true, message: '请输入showIndex!' }]},
        holdFlag:{rules: [{ required: true, message: '请输入holdFlag!' }]},
        statusCode:{rules: [{ required: true, message: '请输入statusCode!' }]},
        },
        url: {
          add: "/system/busiInfo/add",
          edit: "/system/busiInfo/edit",
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
          this.form.setFieldsValue(pick(this.model,'busiUUID','corpCode','busiType','busiCode','busiName','parentCode','busiMemo','showIndex','paymentFlag','holdFlag','statusCode','showFlag','flowFlag'))
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


    }
  }
</script>

<style lang="less" scoped>

</style>