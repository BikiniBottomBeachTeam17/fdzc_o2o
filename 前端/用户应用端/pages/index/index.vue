<template>
	<view class="page">
		<!-- #ifdef APP||H5 -->
		<!-- APP顶部导航条 -->
		<view class="navBox">
			<!-- 用户头像 -->
			<view class="userImgBox">
				<u-avatar :src="navigation.userUrl" size="80rpx"></u-avatar>
			</view>
			<!-- 搜索栏 -->
			<view class="searchBox">
				<u-search placeholder="搜索帖子/商家/闲置物品" :show-action="false" :disabled="true" height="70rpx"
					searchIconSize="40rpx"></u-search>
			</view>
			<!-- 右侧小图标 -->
			<view class="navIcon">
				<i class="iconfont icon-notification" style="margin-right:30rpx;"></i>
				<i class="iconfont icon-scanning"></i>
			</view>
		</view>
		<!-- 占位符 -->
		<u-gap height="calc(120rpx + var(--status-bar-height))"></u-gap>
		<!-- #endif -->
		<!-- 首页轮播图 -->
		<view class="silde">
			<!-- <view class="sildeBg"></view> -->
			<u-swiper :list="silder" keyName="image" showTitle :autoplay="false" circular height="400rpx"
				radius="16rpx"></u-swiper>
		</view>
		<!-- 首页宫格导航 -->
		<view class="iconNav">
			<u-grid :border="false" col="4">
				<u-grid-item v-for="(listItem,listIndex) in iconNav" :key="listIndex" class="gridItem">
					<u--image :showLoading="true" :src="listItem.img" width="96rpx" height="96rpx" shape="circle">
					</u--image>
					<text class="grid-text">{{listItem.title}}</text>
				</u-grid-item>
			</u-grid>
		</view>

		<!-- 标签功能选择 -->
		<view class="funListBox">
			<u-tabs :list="funList" :scrollable="false" lineWidth="200rpx" lineHeight="8rpx" lineColor="#0aa0fa"
				:activeStyle="{
					fontSize:'30rpx',
					color:'#0aa0fa',
					fontWeight: 'bold',
					transform: 'scale(1.05)'
				}" :inactiveStyle="{
					fontSize:'30rpx',
				    color: '#606266',
				    transform: 'scale(1)'
				}" :itemStyle="{
					height:'80rpx'
				}" @click="funChange">
			</u-tabs>
		</view>

		<!-- 最新通知 -->
		<view class="newNoticBox" v-if="funNum==0">
			<!-- <u-empty mode="list" icon="http://cdn.uviewui.com/uview/empty/list.png" width="60%" textSize="32rpx" v-if="newNotice.length==0"></u-empty> -->
			<view class="newNoticItem" style="position: relative;" v-for="(item,index) in newNotice" :key="index">
				<u--text class="newNoticTitle" :lines="1" :text="item.title" size="34rpx"></u--text>
				<view class="newNoticInfo" style="position: absolute;bottom: 22rpx;width: 95%;">
					<view style="width: 80%;float: left;">
						<u--text class="newNoticTitle" :lines="1" :text="item.source+'·'+item.releaseTime" size="28rpx"
							color="rgb(110,110,110)"></u--text>
					</view>
					<view style="width: 20%;float: right;">
						<u--text class="newNoticTitle" :lines="1" prefixIcon="eye" :text="item.traffic" size="28rpx"
							align="right" color="rgb(110,110,110)"></u--text>
					</view>
				</view>
				<!-- <text>{{item.title}}</text> -->
			</view>
		</view>







		<!-- 底部导航 -->
		<tabbar :value="0"></tabbar>
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
				// 顶部导航数据
				navigation: {
					userUrl: 'https://cdn.uviewui.com/uview/album/1.jpg',
				},
				// 导航条
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
				// 宫格导航
				iconNav: [{
						img: 'https://cdn.uviewui.com/uview/album/1.jpg',
						title: '校园导航'
					},
					{
						img: 'https://cdn.uviewui.com/uview/album/1.jpg',
						title: '校园导航'
					},
					{
						img: 'https://cdn.uviewui.com/uview/album/1.jpg',
						title: '校园导航'
					},
					{
						img: 'https://cdn.uviewui.com/uview/album/1.jpg',
						title: '校园导航'
					},
					{
						img: 'https://cdn.uviewui.com/uview/album/1.jpg',
						title: '校园导航'
					},
					{
						img: 'https://cdn.uviewui.com/uview/album/1.jpg',
						title: '校园导航'
					},
					{
						img: 'https://cdn.uviewui.com/uview/album/1.jpg',
						title: '校园导航'
					},
					{
						img: 'https://cdn.uviewui.com/uview/album/1.jpg',
						title: '更多'
					},
				],
				// 平台公告
				notice: {
					value: '平台公告'
				},
				// 首页功能选择
				funList: [{
						name: "最新通知"
					},
					{
						name: "校园店铺"
					},
					{
						name: "闲置上新"
					},
				],
				funNum: 0,
				//最新公告
				newNotice: [
					{
						title: "关于从4月11日起恢复线下教学的通知",
						source: "福州大学至诚学院",
						releaseTime: "2022.04.08",
						traffic: "200"
					},
					{
						title: "关于从4月11日起恢复线下教学的通知",
						source: "福州大学至诚学院",
						releaseTime: "2022.04.08",
						traffic: "200"
					}
				],
				// 底部数据
				tabbar: {
					value: 0,
				}

			}
		},
		onLoad() {
			uni.hideTabBar({})
		},
		methods: {
			//功能按钮点击
			funChange(e) {
				console.log(e.index)
				this.funNum = e.index
			},
			// 底部点击事件
			tabbarChange(e) {
				console.log(e)
			}
		}
	}
</script>

<style lang="scss">
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

		.userImgBox {
			width: 80rpx;
			float: left;
			box-sizing: border-box;
			margin-right: 20rpx;
		}

		.searchBox {
			padding-top: 5rpx;
			box-sizing: border-box;
			width: calc(100% - 300rpx);
			float: left;
		}

		.navIcon {
			float: right;
			line-height: 90rpx;
			margin-right: 30rpx;
			box-sizing: border-box;

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

	.iconNav {
		margin: 0 16rpx;
		padding: 16rpx 0;
		border-radius: 16rpx;
		box-sizing: border-box;
		background-color: #FFFFFF;

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

	.funListBox {
		background-color: #FFFFFF;
		margin-top: 16rpx;
	}

	.newNoticBox {
		background: rgb(240, 240, 240);
		padding: 16rpx;
		box-sizing: border-box;

		.newNoticItem {
			height: 200rpx;
			background-color: #FFFFFF;
			margin-bottom: 16rpx;
			padding: 16rpx;
			box-sizing: border-box;
			border-radius: 16rpx;
		}
	}
</style>
