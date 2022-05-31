<template>
	<!-- 登录页面 -->
	<view class="page" style="background-color: #FFFFFF;">
		<!-- 登录顶部 -->
		<view class="login_header">
			<view class="backBox" @click="backTo">
				<i class="iconfont icon-31fanhui1" style="color: #FFFFFF;"></i>
			</view>
			<view class="registerBox">
				<u--text text="注册" size="32rpx" color="#FFFFFF" @click="goRegister"></u--text>
			</view>
		</view>
		<!-- 登录模式 -->
		<view class="login_type">
			<!-- 账号密码登录 -->
			<view class="acc_pwd">
				<view class="inputBox">
					<!-- 图标 -->
					<i class="iconfont icon-shequ" style="color: rgb(220, 220, 220);"></i>
					<u--input placeholder="请输入手机号" border="none" v-model="userInfo.userAccount" type="number"
						fontSize="26rpx"></u--input>
				</view>
				<view class="inputBox">
					<i class="iconfont icon-mima" style="color: rgb(220, 220, 220);"></i>
					<u-input placeholder="请输入密码" border="none" v-model="userInfo.userPassword" type="password"
						fontSize="26rpx">
						<template slot="suffix">
							<u--text text="无法登录?" size="28rpx" color="#0aa0fa"></u--text>
						</template>
					</u-input>
				</view>
				<!-- 登录按钮 -->
				<view class="loginBtn">
					<u-button type="primary" :plain="false" shape="circle" text="登 录" color="#0aa0fa"
						:disabled="(userInfo.userAccount!=''&&userInfo.userPassword!='')?false:true" @click="login">
					</u-button>
				</view>
			</view>
			<u-toast ref="uToast"></u-toast>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userInfo: {
					userAccount: '',
					userPassword: '',
				}
			}
		},
		onLoad() {
			uni.removeStorageSync("userAccount")
			uni.removeStorageSync("token")
		},
		methods: {
			backTo() {
				uni.switchTab({
					url: '/pages/index/index'
				})
			},
			goRegister() {
				uni.navigateTo({
					url: '/pages/user/register'
				})
			},
			login() {
				var self = this
				this.$request({
					url: '/user/login',
					method: 'POST',
					data: self.userInfo,
				}).then(res => {
					console.log(res) 	
					if (res.code == 200) {
						uni.setStorageSync("userAccount", res.data.userAccount)
						uni.setStorageSync("token", res.data.token)
						self.$refs.uToast.show({
							message: '登录成功',
							position: 'bottom',
							duration: 1000,
							complete() {
								uni.switchTab({
									url: '/pages/user/index'
									})
							}
						})
					} else {
						self.$refs.uToast.show({
							message: res.msg,
							position: 'bottom',
							duration: 2000
						})
					}
				})
			}
		}
	}
</script>

<style lang="scss">
	.login_header {
		height: calc(var(--status-bar-height) + 270rpx);
		background-color: #0aa0fa;
		padding: 0 32rpx;
		padding-top: calc(var(--status-bar-height) + 32rpx);
		box-sizing: border-box;

		.backBox {
			float: left;
		}

		.registerBox {
			float: right;
		}
	}

	.login_type {
		background-color: #FFFFFF;
		padding: 80rpx 60rpx;

		.inputBox {
			display: flex;
			height: 40rpx;
			line-height: 40rpx;
			border-bottom: 1rpx solid rgb(245, 245, 245);
			margin-bottom: 20rpx;
			padding: 24rpx 16rpx;

			.iconfont {
				margin-right: 16rpx;
			}
		}

		.loginBtn {
			margin-top: 66rpx;
		}
	}
</style>
