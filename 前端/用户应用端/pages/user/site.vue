<template>
	<view class="page">
		<view class="headerBox">
			<view class="headerTitle">
				<view class="backBox" @click="backTo">
					<i class="iconfont icon-31fanhui1"></i>
				</view>
				<view class="titleBox">
					我的地址
				</view>
				<view class="rightBox">
					管理
				</view>
			</view>
		</view>
		<view class="siteBox">
			<view class="siteItem">
				<view class="siteInfo">
					<u--text text="姓名"></u--text>
					<u--text text="中区2栋 404"></u--text>
				</view>
				<view class="siteEdit">
					<i class="iconfont icon-bianji"></i>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		methods:{
			backTo(){
				uni.navigateBack({})
			}
		}
	}
</script>

<style lang="scss">
	.headerBox{
		height: calc(100rpx + var(--status-bar-height));
		padding-top: var(--status-bar-height);
		box-sizing: border-box;
		background-color: #FFFFFF;
		.headerTitle{
			display: flex;
			flex-direction: row;
			justify-content: space-between;
			height: 100rpx;
			line-height: 100rpx;
			text-align: center;
			padding: 0 18rpx;
			.rightBox{
				color: #3C9CFF;
			}
		}
	}
	.siteBox{
		.siteItem{
			display: flex;
			flex-direction: row;
			justify-content: space-between;
			height: 100rpx;
			background-color: #FFFFFF;
			padding: 10rpx 22rpx;
			.siteInfo{
				display: flex;
				flex-direction:column;
				justify-content: flex-start;
				align-items: center;
			}
		}
	}
</style>
