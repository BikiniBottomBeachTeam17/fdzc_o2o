<template>
	<view class="page">
		<!-- 顶部 -->
		<view class="headerBox">
			<view class="tabsBox">
				<u-tabs :list="funList" :scrollable="false" lineWidth="120rpx" lineHeight="8rpx" lineColor="#0aa0fa"
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
		</view>
		<view class="contentBox">
			<view class="newsItem">
				<view class="gridBox">
					<u-grid :border="false" col="3">
						<u-grid-item v-for="(listItem,listIndex) in iconNav" :key="listIndex"  @click="iconClick(listItem)" class="gridItem">
							<u--image :showLoading="true" :src="listItem.img" width="96rpx" height="96rpx"
								shape="circle">
							</u--image>
							<text class="grid-text">{{listItem.title}}</text>
						</u-grid-item>
					</u-grid>
				</view>
				<!-- 提醒内容 -->
				<view class="content">
					<view class="contentItem">
						<view class="userAvatar">
							
						</view>
						<view class="contentInfo">
							
						</view>
						<view class="contentImg">
							
						</view>
					</view>
				</view>
			</view>
			
		</view>
		<tarbar :value="2"></tarbar>
	</view>
</template>

<script>
	import tarbar from '../../components/public/tabbar.vue'
	export default {
		components:{
			tarbar
		},
		onLoad() {
			uni.hideTabBar({})
		},
		data() {
			return {
				funList: [{
						name: "消息"
					},
					{
						name: "私信"
					}
				],
				iconNav: [{
					img: 'https://cdn.uviewui.com/uview/album/1.jpg',
					title: '小圆通知'
				}, {
					img: 'https://cdn.uviewui.com/uview/album/1.jpg',
					title: '收到的评论'
				}, {
					img: 'https://cdn.uviewui.com/uview/album/1.jpg',
					title: 'XXXXXX'
				}]
			}
		},
		methods: {
			// 改变顶部导航
			funChange(e) {
				console.log(e)
			},
			// 点击
			iconClick(e){
				console.log(e)
			}
		}
	}
</script>

<style lang="scss">
	.headerBox {
		height: calc(var(--status-bar-height) + 80rpx);
		background-color: #FFFFFF;
		padding-top: var(--status-bar-height);
		box-sizing: border-box;
		.tabsBox {
			width: 300rpx;
			margin: 0 auto;
		}
	}

	.contentBox {
		.newsItem {
			.gridBox {
				background-color: #FFFFFF;
				padding-top: 32rpx;
				padding-bottom: 16rpx;
				margin-bottom: 16rpx;
				box-sizing: border-box;

				.grid-text {
					margin-top: 10rpx;
					font-size: 28rpx;
				}
			}
			.content{
				
			}
		}
		
	}
</style>
