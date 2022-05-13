<template>
	<view class="">
		<!-- 底部导航 -->
		<u-tabbar :value="value" @change="change"  activeColor="#0aa0fa">
			<u-tabbar-item :text="item.text" v-for="(item,index) in list" :key="index">
				<i class="iconfont" :class="item.iconPath" slot="active-icon" style="color: #0aa0fa;"></i>
				<i class="iconfont" :class="item.iconPath" slot="inactive-icon"></i>
			</u-tabbar-item>
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
				uni.switchTab({
					url:this.list[index].url
				})
			}
		}
	}
</script>

<style lang="scss">
</style>
