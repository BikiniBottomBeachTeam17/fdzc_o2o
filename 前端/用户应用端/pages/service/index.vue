<template>
	<view class="page">
		<view class="headerBox">
			<u-navbar title="所有服务" :autoBack="true" height="100rpx" leftIconSize="40rpx">
			</u-navbar>
		</view>
		<!-- 轮播图 -->
		<view class="silde">
			<u-swiper :list="silder" keyName="image" showTitle :autoplay="false" circular height="300rpx"
				radius="16rpx"></u-swiper>
		</view>
		<!-- 最近使用 -->

		<!-- 所有功能 -->
		<view class="allFuncBox">
			<!-- 校园功能 -->
			<view class="funBox" v-for="(listItem,index) in funList" :key="index">
				<!-- 标题 -->
				<view class="funTitle">
					<view class="showLine"></view>
					{{listItem.title}}
				</view>
				<u-grid :border="false" col="4">
					<u-grid-item v-for="(item,itemIndex) in listItem.funItem" :key="itemIndex" class="gridItem">
						<u--image :showLoading="true" :src="item.img" width="96rpx" height="96rpx" shape="circle">
						</u--image>
						<text class="grid-text">{{item.title}}</text>
					</u-grid-item>
				</u-grid>
			</view>
		</view>

		<!-- <tabbar :value="1"></tabbar> -->
	</view>
</template>

<script>
	import tabbar from '../../components/public/tabbar.vue'
	export default {
		components: {
			tabbar
		},
		data() {
			return {
				silder: [{
						image: 'https://cdn.uviewui.com/uview/swiper/swiper2.png',
						title: '昨夜星辰昨夜风，画楼西畔桂堂东',
					},
					{
						image: 'https://cdn.uviewui.com/uview/swiper/swiper1.png',
						title: '身无彩凤双飞翼，心有灵犀一点通'
					},
					{
						image: 'https://cdn.uviewui.com/uview/swiper/swiper3.png',
						title: '谁念西风独自凉，萧萧黄叶闭疏窗，沉思往事立残阳'
					}
				],
				funList: [{
						title: '校内工具',
						funItem: [{
								img: 'https://cdn.uviewui.com/uview/album/1.jpg',
								title: '校园指南'
							},
							{
								img: 'https://cdn.uviewui.com/uview/album/1.jpg',
								title: '课表查询'
							},
							{
								img: 'https://cdn.uviewui.com/uview/album/1.jpg',
								title: '校历查询'
							},
							{
								img: 'https://cdn.uviewui.com/uview/album/1.jpg',
								title: '校内头条'
							},
							{
								img: 'https://cdn.uviewui.com/uview/album/1.jpg',
								title: '组织社团'
							},
							{
								img: 'https://cdn.uviewui.com/uview/album/1.jpg',
								title: '校园电话簿'
							},
						]
					},
					{
						title: '校园生活',
						funItem: [{
								img: 'https://cdn.uviewui.com/uview/album/1.jpg',
								title: '美食推荐'
							},
							{
								img: 'https://cdn.uviewui.com/uview/album/1.jpg',
								title: '校园超市'
							},
							{
								img: 'https://cdn.uviewui.com/uview/album/1.jpg',
								title: '跑腿服务'
							},
							{
								img: 'https://cdn.uviewui.com/uview/album/1.jpg',
								title: '二手市场'
							},
							{
								img: 'https://cdn.uviewui.com/uview/album/1.jpg',
								title: '校园墙'
							},
							{
								img: 'https://cdn.uviewui.com/uview/album/1.jpg',
								title: '众包兼职'
							}
						]
					}
				]

			}
		}
	}
</script>

<style lang="scss">
	.headerBox {
		height: calc(100rpx + var(--status-bar-height));
		background-color: #FFFFFF;
		line-height: 100rpx;
		text-align: center;
		box-sizing: border-box;
		position: sticky;
		top: 0;
		left: 0;
		z-index: 1;

		.navIcon {
			line-height: 100rpx;
			box-sizing: border-box;
			position: absolute;
			top: 0;
			right: 30rpx;

			.iconfont {
				font-size: 50rpx;
				color: rgb(110, 110, 110);
			}
		}
	}

	.silde {
		padding: 16rpx;
		box-sizing: border-box;
	}

	.allFuncBox {
		padding: 0 16rpx;
		background-color: rgb(240, 240, 240);

		.funTitle {
			padding-left: 16rpx;
			margin-bottom: 16rpx;
			height: 40rpx;

			.showLine {
				float: left;
				width: 8rpx;
				height: 100%;
				line-height: 40rpx;
				background-color: #007AFF;
				margin-right: 20rpx;
			}
		}

		.funBox {
			background-color: #FFFFFF;
			padding: 18rpx 0;
			border-radius: 16rpx;
			margin-bottom: 16rpx;
		}

		.gridItem:nth-child(1),
		.gridItem:nth-child(2),
		.gridItem:nth-child(3),
		.gridItem:nth-child(4) {
			margin-bottom: 16rpx;
		}

		.grid-text {
			margin-top: 10rpx;
			font-size: 28rpx;
		}
	}
</style>
