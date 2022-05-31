<template>
	<view class="page">
		<view class="header">
			<view class="backBox" @click="backTo">
				<i class="iconfont icon-31fanhui1" style="color: rgb(160,160,160);"></i>
			</view>
			<view class="headerTitle">
				设置
			</view>
			<view style="width: 50rpx;float: right;"></view>
		</view>
		<view class="groupBox">
			<u-cell-group>
				<u-cell title="个人资料设置" isLink url="/pages/user/userInfoSetting"></u-cell>
				<u-cell title="地址管理" isLink url="/pages/user/site"></u-cell>
			</u-cell-group>
		</view>
		<view class="groupBox">
			<u-cell-group>
				<u-cell title="关于易诚小圆" isLink></u-cell>
				<u-cell title="XXX" isLink></u-cell>
			</u-cell-group>
		</view>
		<!-- 退出登录 -->
		<view class="logout">
			<u-button type="error" color="rgb(245,108,108)" :plain="false" text="退 出 登 录" @click="logoutShow=true">
			</u-button>
		</view>
		<u-popup :show="logoutShow" :safeAreaInsetBottom="true" :safeAreaInsetTop="true" mode="center" :round="6"
			:overlay="true" :closeable="false" :closeOnClickOverlay="true" @close="LogoutBoxClose" >
			<view class="closeBox">
				<view class="closeBoxTitle">
					<text>您确定要退出？</text>
				</view>
				<view class="closeBoxBtn">
					<view class="itemBtn" @click="LogoutBoxClose">
						取消
					</view>
					<view class="itemBtn" @click="logout">
						确认
					</view>
				</view>
			</view>
		</u-popup>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				logoutShow: false
			}
		},
		methods: {
			backTo() {
				uni.navigateBack({})
			},
			logout() {
				uni.removeStorageSync('userInfo')
				uni.removeStorageSync('userId')
				uni.removeStorageSync('token')
				uni.switchTab({
					url:'/pages/index/index'
				})
			},
			LogoutBoxClose() {
				this.logoutShow = false
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

	.groupBox {
		background-color: #FFFFFF;
		margin-top: 16rpx;
	}

	.logout {
		margin-top: 16rpx;
		padding: 0 16rpx;
	}

	.closeBox{
		width: 500rpx;
		height: 250rpx;
		.closeBoxTitle{
			height: 150rpx;
			line-height: 150rpx;
			text-align: center;
		}
		.closeBoxBtn{
			height: 100rpx;
			display: flex;
			.itemBtn{
				width: 50%;
				text-align: center;
				height: 100rpx;
				line-height: 100rpx;
			}
			.itemBtn:first-child{
				border-top: 1px solid rgb(240,240,240);
			}
			.itemBtn:last-child{
				background-color: #0aa0fa;
				color: #FFFFFF;
			}
		}
	}
	
</style>
