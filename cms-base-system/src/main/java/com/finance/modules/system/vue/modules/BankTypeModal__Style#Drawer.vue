<template>
  <a-drawer
      :title="title"
      :width="800"
      placement="right"
      :closable="false"
      @close="close"
      :visible="visible"
  >

    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
      
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="typeUUID">
          <a-input-number v-decorator="[ 'typeUUID', validatorRules.typeUUID ]" />
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
          label="bankType">
          <a-input-number v-decorator="[ 'bankType', validatorRules.bankType ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="typeCode">
          <a-input placeholder="请输入typeCode" v-decorator="['typeCode', validatorRules.typeCode ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="typeName">
          <a-input placeholder="请输入typeName" v-decorator="['typeName', validatorRules.typeName ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="typeMemo">
          <a-input placeholder="请输入typeMemo" v-decorator="['typeMemo', {}]" />
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
          label="holdFlag">
          <a-input-number v-decorator="[ 'holdFlag', validatorRules.holdFlag ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="statusCode">
          <a-input-number v-decorator="[ 'statusCode', validatorRules.statusCode ]" />
        </a-form-item>
		
      </a-form>
    </a-spin>
    <a-button type="primary" @click="handleOk">确定</a-button>
    <a-button type="primary" @click="handleCancel">取消</a-button>
  </a-drawer>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from "moment"

  export default {
    name: "BankTypeModal",
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
        typeUUID:{rules: [{ required: true, message: '请输入typeUUID!' }]},
        corpCode:{rules: [{ required: true, message: '请输入corpCode!' }]},
        bankType:{rules: [{ required: true, message: '请输入bankType!' }]},
        typeCode:{rules: [{ required: true, message: '请输入typeCode!' }]},
        typeName:{rules: [{ required: true, message: '请输入typeName!' }]},
        showIndex:{rules: [{ required: true, message: '请输入showIndex!' }]},
        holdFlag:{rules: [{ required: true, message: '请输入holdFlag!' }]},
        statusCode:{rules: [{ required: true, message: '请输入statusCode!' }]},
        },
        url: {
          add: "/system/bankType/add",
          edit: "/system/bankType/edit",
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
          this.form.setFieldsValue(pick(this.model,'typeUUID','corpCode','bankType','typeCode','typeName','typeMemo','showIndex','holdFlag','statusCode'))
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
/** Button按钮间距 */
  .ant-btn {
    margin-left: 30px;
    margin-bottom: 30px;
    float: right;
  }
</style>