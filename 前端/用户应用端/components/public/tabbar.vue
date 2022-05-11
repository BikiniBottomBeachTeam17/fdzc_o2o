<template>
	<view class="">
		<!-- 底部导航 -->
		<u-tabbar :value="value" @change="change" size="50rpx" activeColor="#0aa0fa">
			<u-tabbar-item text="首页" v-for="(item,index) in list" :key="index">
				<i class="iconfont" :class="item.iconPath" slot="active-icon" style="color: #0aa0fa;"></i>
				<i class="iconfont" :class="item.iconPath" slot="inactive-icon"></i>
			</u-tabbar-item>
			<!-- <u-tabbar-item text="服务">
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
			</u-tabbar-item> -->
		</u-tabbar>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				list: [
					{
						url: '/pages/index/index',
						iconPath: 'icon-home',
						text: '首页'
					},
					{
						url: '/pages/index/index',
						iconPath: 'icon-modular',
						text: '服务'
					},
					{
						url: '/pages/index/index',
						iconPath: 'icon-notification',
						text: '消息'
					},
					{
						url: '/pages/user/index',
						iconPath: 'icon-user',
						text: '我的'
					}
				]
			}
		},
		props: {
			value:0,
		},
		methods: {
			change(index) {
				uni.navigateTo({
					url:this.list[index].url
				})
			}
		}
	}
</script>

<style>
</style>
