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
          label="leadUUID">
          <a-input-number v-decorator="[ 'leadUUID', validatorRules.leadUUID ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="leadTitle">
          <a-input placeholder="请输入leadTitle" v-decorator="['leadTitle', validatorRules.leadTitle ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="leadContent">
          <a-input placeholder="请输入leadContent" v-decorator="['leadContent', validatorRules.leadContent ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="leadSign">
          <a-input placeholder="请输入leadSign" v-decorator="['leadSign', validatorRules.leadSign ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="dataSource">
          <a-input placeholder="请输入dataSource" v-decorator="['dataSource', {}]" />
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
          label="menuCode">
          <a-input placeholder="请输入menuCode" v-decorator="['menuCode', validatorRules.menuCode ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="isMain">
          <a-input-number v-decorator="[ 'isMain', validatorRules.isMain ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="isInit">
          <a-input-number v-decorator="[ 'isInit', validatorRules.isInit ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="isSelf">
          <a-input-number v-decorator="[ 'isSelf', validatorRules.isSelf ]" />
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
    name: "SystemLeadInfoModal",
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
        leadUUID:{rules: [{ required: true, message: '请输入leadUUID!' }]},
        leadTitle:{rules: [{ required: true, message: '请输入leadTitle!' }]},
        leadContent:{rules: [{ required: true, message: '请输入leadContent!' }]},
        leadSign:{rules: [{ required: true, message: '请输入leadSign!' }]},
        corpCode:{rules: [{ required: true, message: '请输入corpCode!' }]},
        menuCode:{rules: [{ required: true, message: '请输入menuCode!' }]},
        isMain:{rules: [{ required: true, message: '请输入isMain!' }]},
        isInit:{rules: [{ required: true, message: '请输入isInit!' }]},
        isSelf:{rules: [{ required: true, message: '请输入isSelf!' }]},
        },
        url: {
          add: "/system/systemLeadInfo/add",
          edit: "/system/systemLeadInfo/edit",
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
          this.form.setFieldsValue(pick(this.model,'leadUUID','leadTitle','leadContent','leadSign','dataSource','corpCode','menuCode','isMain','isInit','isSelf'))
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