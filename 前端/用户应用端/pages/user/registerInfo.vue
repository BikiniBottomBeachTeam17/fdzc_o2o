<template>
	<view class="page">
		<view class="header">
			<view class="backBox" @click="backTo">
				<i class="iconfont icon-31fanhui1" style="color: rgb(160,160,160);"></i>
			</view>
			<view class="headerTitle">
				个人信息
			</view>
			<view style="width: 50rpx;float: right;"></view>
		</view>
		<!-- 头像上传 -->
		<view class="avatarInputBox">
			<avatar selWidth="200rpx" selHeight="200rpx" :avatarSrc="tempAvarter" @upload="avatarUpload"
				avatarStyle="width: 160rpx; height: 160rpx; border-radius: 100%;border: 1px solid #000;" inner="true"
				style="margin: 0 auto;width: 160rpx;height: 160rpx;"></avatar>
		</view>
		<!-- 详细信息 -->
		<view class="userInfoInputBox">
			<view class="inputBox">
				<!--  -->
				<view class="inputTag">
					<u--text text="昵称" type="info" size="28rpx"></u--text>
				</view>
				<view class="input">
					<u--input placeholder="请输入昵称" border="none" v-model="userInfo.userName" fontSize="26rpx"></u--input>
				</view>
			</view>
			<view class="inputBox">
				<!--  -->
				<view class="inputTag">
					<u--text text="性别" type="info" size="28rpx"></u--text>
				</view>
				<view class="input" style="display: flex;">
					<u-radio-group v-model="userInfo.userGender" placement="row">
						<u-radio :customStyle="{marginRight: '16px'}" v-for="(item, index) in radioSex" :key="index"
							:label="item.name" :name="item.name" size="32rpx" labelSize="26rpx">
						</u-radio>
					</u-radio-group>
				</view>
			</view>
			<view class="inputBox">
				<!--  -->
				<view class="inputTag">
					<u--text text="电子邮箱" type="info" size="28rpx"></u--text>
				</view>
				<view class="input">
					<u--input placeholder="请输入电子邮箱" border="none" v-model="userInfo.userEmail" fontSize="26rpx">
					</u--input>
				</view>
			</view>
			<view class="inputBox">
				<!--  -->
				<view class="inputTag">
					<u--text text="联系方式" type="info" size="28rpx"></u--text>
				</view>
				<view class="input">
					<u--input placeholder="请输入手机号" border="none" type="number" v-model="userInfo.userPhone"
						fontSize="26rpx"></u--input>
				</view>
			</view>
		</view>
		<!-- 注册按钮 -->
		<view class="registerBtn">
			<u-button type="primary" :plain="false" shape="circle" text="保 存" color="#0aa0fa" @click="register">
			</u-button>
		</view>
		<u-toast ref="uToast"></u-toast>
	</view>
</template>

<script>
	import avatar from "../../components/yq-avatar/yq-avatar.vue";
	export default {
		components: {
			avatar
		},
		data() {
			return {
				userInfo: {
					userAccount: '',
					userPassword: '',
					userPortrait: '',
					userName: '',
					userGender: '男生',
					userEmail: '',
					userPhone: ''
				},
				tempAvarter: '',
				radioSex: [{
						name: '男生'
					},
					{
						name: '女生'
					}
				]
			}
		},
		onLoad(res) {
			if (!res.userAccount ||
				!res.userPassword ||
				(!res.userAccount.match(/^[a-zA-Z0-9_-]{8,16}$/)) ||
				(!res.userPassword.match(/^[a-zA-Z0-9._-]{6,16}$/))) {
				uni.redirectTo({
					url: '/pages/user/register'
				})
			}
			this.userInfo.userAccount = res.userAccount
			this.userInfo.userPassword = res.userPassword
			console.log(this.userInfo)
		},
		methods: {
			avatarUpload(pic) {
				console.log(pic)
				var self = this
				this.tempAvarter = pic.path
				this.$upImg(pic.path).then(res=>{
					console.log(res)
					self.userInfo.userPortrait = res
				})
			},
			backTo() {
				uni.navigateBack({})
			},
			register() {
				if(this.userInfo.userName==''){
					this.$refs.uToast.show({
						message: '昵称不能为空哦',
						position: 'bottom',
						duration: 1000
					})
					return
				}
				var self = this
				this.userInfo.userGender = (this.userInfo.userGender=='男生')?'1':'0'
				this.$request({
					method: 'POST',
					url: '/user/register',
					data: self.userInfo,
				}).then(res=>{
					if (res.code == 200) {
						self.$refs.uToast.show({
							message: '注册成功\n正在跳转登录页',
							position: 'bottom',
							duration: 2000,
							complete() {
								uni.redirectTo({
									url: '/pages/user/login',
								})
							}
						})
					}
				})
			}
		}
	}
</script>

<style lang="scss">
	.header {
		height: calc(var(--status-bar-height) + 100rpx);
		padding: 0 32rpx;
		padding-top: var(--status-bar-height);
		box-sizing: border-box;
		line-height: 100rpx;
		background-color: #FFFFFF;

		.backBox {
			width: 50rpx;
			float: left;
		}

		.headerTitle {
			float: left;
			width: calc(100% - 100rpx);
			text-align: center;
		}
	}

	.avatarInputBox {
		background-color: #FFFFFF;
		padding: 50rpx 0;
		margin-bottom: 16rpx;
	}

	.userInfoInputBox {
		padding: 10rpx 20rpx;
		background-color: #FFFFFF;

		.inputBox {
			display: flex;
			height: 50rpx;
			line-height: 50rpx;
			border-bottom: 1rpx solid rgb(245, 245, 245);
			margin-bottom: 20rpx;
			padding: 24rpx 16rpx;

			.inputTag {
				padding-left: 20rpx;
				width: 200rpx;
				box-sizing: border-box;
			}
		}

		.inputBox:last-child {
			border-bottom: none;
			margin-bottom: 0;
		}
	}

	.registerBtn {
		margin-top: 40rpx;
		padding: 0 60rpx;

	}
</style>
