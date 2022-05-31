<template>
	<view class="page" style="background-color: #FFFFFF;">
		<view class="register_header">
			<view class="backBox" @click="backTo">
				<i class="iconfont icon-31fanhui1" style="color: rgb(160,160,160);"></i>
			</view>
			<view class="registerTitle">
				注册
			</view>
			<view style="width: 50rpx;float: right;"></view>
		</view>
		<view class="registerBox">
			<view class="acc_pwd">
				<view class="inputBox">
					<!-- 图标 -->
					<i class="iconfont icon-shequ" style="color: rgb(220, 220, 220);"></i>
					<u--input placeholder="请输入账号" border="none" v-model="userAccount" fontSize="26rpx"></u--input>
				</view>
				<view class="inputBox">
					<i class="iconfont icon-mima" style="color: rgb(220, 220, 220);"></i>
					<u-input placeholder="请输入密码(6-16位)" border="none" v-model="userPassword" type="password" fontSize="26rpx"></u-input>
				</view>
				<view class="inputBox">
					<i class="iconfont icon-mima" style="color: rgb(220, 220, 220);"></i>
					<u-input placeholder="请确认密码" border="none" v-model="checkPassword" type="password" fontSize="26rpx"></u-input>
				</view>
				<!-- 注册按钮 -->
				<view class="registerBtn">
					<u-button type="primary" :plain="false" shape="circle" text="下一步" color="#0aa0fa" @click="registerNext"
					 :disabled="(userAccount!=''&&userPassword!=''&&checkPassword!='')?false:true"></u-button>
				</view>
				<view class="registerTig">
					<u--text text="注册即表示同意《隐私权声明》" align="center" color="rgb(160,160,160)"></u--text>
				</view>
			</view>
		</view>
		<u-toast ref="uToast"></u-toast>
	</view>
</template>

<script>
	export default {
		data(){
			return{
				userAccount:'',
				userPassword:'',
				checkPassword:''
			}
		},
		methods:{
			backTo(){
				uni.navigateBack({})
			},
			registerNext(){
				var self=this
				if(!this.userAccount.match(/^[a-zA-Z0-9_-]{8,16}$/)){
					this.$refs.uToast.show({
						message:'账号不符合要求\n账号长度:8-16位\n只允许字母、数字及下划线',
						position:'bottom',
						duration:1000
					})
					return
				}
				if(!this.userPassword.match(/^[a-zA-Z0-9._-]{6,16}$/)){
					this.$refs.uToast.show({
						message:'密码不符合要求\n密码长度:6-16位',
						position:'bottom',
						duration:1000
					})
					return
				}
				if(this.userPassword!=this.checkPassword){
					this.$refs.uToast.show({
						message:'两次密码不一致',
						position:'bottom',
						duration:1000
					})
					return
				}
				this.$request({
					url:'/user/checkUserAccount?userAccount='+self.userAccount,
				}).then(res=>{
					if(res.code==200){
						uni.navigateTo({
							url:'/pages/user/registerInfo?userAccount='+self.userAccount+'&userPassword='+self.userPassword
						})
					}else{
						self.$refs.uToast.show({
							message:'当前账号已被使用',
							position:'bottom',
							duration:1000
						})
					}
				})
				
			}
		}
	}
</script>

<style lang="scss">
	.register_header{
		height: calc(var(--status-bar-height) + 100rpx);
		padding: 0 32rpx;
		padding-top: var(--status-bar-height);
		box-sizing: border-box;
		line-height: 100rpx;
		.backBox {
			width: 50rpx;
			float: left;
		}
		.registerTitle {
			float: left;
			width: calc(100% - 100rpx);
			text-align: center;
		}
	}
	.registerBox{
		background-color: #FFFFFF;
		padding: 0 60rpx ;
		.inputBox {
			display: flex;
			height: 40rpx;
			line-height: 40rpx;
			border-bottom:1rpx solid rgb(245, 245, 245);
			margin-bottom: 20rpx;
			padding: 24rpx 16rpx;
			.iconfont{
				margin-right: 16rpx;
			}
		}
		.registerBtn{
			margin-top:160rpx;
		}
		.registerTig{
			margin-top:60rpx;
		}
	}
</style>
