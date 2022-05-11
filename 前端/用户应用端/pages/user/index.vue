<template>
	<view class="page">
		<!-- #ifdef APP||H5 -->
		<!-- APP顶部导航条 -->
		<view class="navBox">
			
		</view>
		<!-- 占位符 -->
		<u-gap height="calc(120rpx + var(--status-bar-height))"></u-gap>
		<!-- #endif -->
		
		
		
		
		
		
		
		
		
		<!-- 底部导航 -->
		<!-- <u-tabbar :value="tabbar.value" @change="tabbarChange" size="50rpx" activeColor="#0aa0fa">
			<u-tabbar-item text="首页">
				<i class="iconfont icon-home" slot="active-icon" style="color: #0aa0fa;"></i>
				<i class="iconfont icon-home" slot="inactive-icon"></i>
			</u-tabbar-item>
			<u-tabbar-item text="服务">
				<i class="iconfont icon-modular" slot="active-icon" style="color: #0aa0fa;"></i>
				<i class="iconfont icon-modular" slot="inactive-icon"></i>
			</u-tabbar-item>
			<u-tabbar-item text="消息">
				<i class="iconfont icon-notification" slot="active-icon" style="color: #0aa0fa;"></i>
				<i class="iconfont icon-notification" slot="inactive-icon"></i>
			</u-tabbar-item>
			<u-tabbar-item text="我的">
				<i class="iconfont icon-user" slot="active-icon" style="color: #0aa0fa;"></i>
				<i class="iconfont icon-user" slot="inactive-icon"></i>
			</u-tabbar-item>
		</u-tabbar> -->
		<tabbar :value="3"></tabbar>
	</view>
</template>

<script>
	import tabbar from '../../components/public/tabbar.vue'
	export default {
		components:{
			tabbar
		},
		data() {
			return {
				
				
				
				// 底部数据
				tabbar: {
					value: 3,
				}
			}
		},
		onLoad() {},
		methods: {}
	}
</script>

<style>
	.navBox {
		width: 100%;
		box-sizing: border-box;
		height: calc(120rpx + var(--status-bar-height));
		padding-top: calc(var(--status-bar-height) + 20rpx);
		padding-left: 30rpx;
		background-color: #FFFFFF;
		position: fixed;
		top: 0;
		left: 0;
		z-index: 1;
	}
</style>
