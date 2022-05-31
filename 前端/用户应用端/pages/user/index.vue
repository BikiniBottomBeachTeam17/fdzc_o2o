<template>
	<view class="page">
		<!-- #ifdef APP||H5 -->
		<!-- APP顶部导航条 -->
		<view class="navBox">
			<!-- 右侧小功能 -->
			<view class="naviIcon">
				<i class="iconfont icon-scanning" style="margin-right: 30rpx;"></i>
				<i class="iconfont icon-shezhi" @click="goSetting"></i>
			</view>
			<!-- 登录按钮 -->
			<view class="loginBtnBox" v-if="isLogin==false">
				<u-button type="primary" :plain="false" shape="circle" text="立即登录/注册" color="#0aa0fa" @click="login"></u-button>
			</view>
			<!-- 头像及主页 -->
			<view class="userInfoBox" v-if="isLogin==true" @click="goSelfHome">
				<!-- 用户头像 -->
				<view class="userAvatar">
					<u-avatar :src="userInfo.userPortrait" size="130rpx"></u-avatar>
				</view>
				<view class="userInfo" >
					<u--text :text="userInfo.userName" size="36rpx" :bold="true"></u--text>
					<!-- <u--text :text="userInfo.userAccount" size="28rpx" color="rgb(180,180,180)"
						style="margin-bottom: 5rpx;">
					</u--text> -->
					<view class="tagsBox">
						<view class="tagsItem" v-if="userInfo.isStudent">
							<u-tag text="学生认证" size="mini" class="tags"></u-tag>
						</view>
						<view class="tagsItem" v-if="!userInfo.isStudent">
							<u-tag text="未认证" type="warning" size="mini" class="tags"></u-tag>
						</view>
					</view>
				</view>
				<view class="userIndexBox" style="float: right;padding-right:16rpx">
					<u--text suffixIcon="arrow-right" iconStyle="font-size: 30rpx" size="26rpx" color="rgb(110,110,110)"
						text="个人主页"></u--text>
				</view>
			</view>
			<!-- 用户数据 -->
			<view class="userInfoNav">
				<u-grid :border="true" :col="3">
					<u-grid-item class="infoNavItem" v-for="(infoItem,index) in infoNav" :key="index">
						<view class="infoNavaData">
							{{infoItem.data}}
						</view>
						<view class="infoNavaTitle">
							{{infoItem.title}}
						</view>
					</u-grid-item>
				</u-grid>
			</view>
			<!-- 商家、跑腿模块 -->
			<view class="Cer_Man_Model">
				<u-row style="height: 100%;">
					<u-col span="6">
						<view class="Cer_Man_Model_Item" style="border-right: 4rpx solid #F99104;">
							<view class="content"
								style="margin: 0 auto;width: 220rpx;display: flex;box-sizing: border-box;">
								<view class="icon" style="margin-right: 10rpx;box-sizing: border-box;">
									<i class="iconfont icon-fuwuchaoshi"
										style="font-size: 60rpx;font-weight: bold;color: #F99104;"></i>
								</view>
								<view class="text" style="">
									<u--text text="全民开店" color="#783C2D" size="32rpx" :bold="true"></u--text>
									<u--text text="校内专属小店" color="#783C2D"></u--text>
								</view>
							</view>
						</view>
					</u-col>
					<u-col span="6">
						<view class="Cer_Man_Model_Item">
							<view class="content"
								style="margin: 0 auto;width: 240rpx;display: flex;box-sizing: border-box;">
								<view class="icon" style="margin-right: 10rpx;box-sizing: border-box;">
									<i class="iconfont icon-diandongche"
										style="font-size: 60rpx;font-weight: bold;color: #F99104;"></i>
								</view>
								<view class="text" style="">
									<u--text text="成为跑腿员" color="#783C2D" size="32rpx" :bold="true"></u--text>
									<u--text text="课余时间赚零钱" color="#783C2D"></u--text>
								</view>
							</view>
						</view>
					</u-col>
				</u-row>
			</view>
		</view>
		<!-- 占位符 -->
		<!-- <u-gap height="calc(520rpx + var(--status-bar-height))"></u-gap> -->
		<!-- #endif -->
		<!-- 功能模块 -->
		<view class="funBox">
			<u-grid :border="false" col="4">
				<u-grid-item v-for="(item,index) in funList" :key="index">
					<i class="iconfont" :class="item.icon"></i>
					<text class="text">{{item.title}}</text>
				</u-grid-item>
			</u-grid>
		</view>
		<!-- 功能模块 -->
		<view class="allFunBox">
			<!-- 标题 -->
			<view class="funCard" v-for="(listItem,index) in allFunList" :key="index">
				<view class="funTitle">
					{{listItem.title}}
				</view>
				<u-grid :border="false" col="4" >
					<u-grid-item v-for="(item,itemIndex) in listItem.funList" :key="itemIndex" class="gridItem" @click="goPage(item)" >
						<u--image :showLoading="true" :src="item.img" width="96rpx" height="96rpx" shape="circle">
						</u--image>
						<text class="grid-text">{{item.title}}</text>
					</u-grid-item>
				</u-grid>
			</view>
		</view>
		<tabbar :value="3"></tabbar>
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
				//是否登录
				isLogin:false,
				// 用户信息,后端获取
				userInfo: {
					userName: '小天',
					userAccount: '123456789',
					userPortrait: 'https://cdn.uviewui.com/uview/album/1.jpg',
					isStudent: true
				},
				// 用户基础数据
				infoNav: [
					{
						title: '动态',
						data: '0'
					}, 
					{
						title: '关注',
						data: '0'
					}, 
					{
						title: '粉丝',
						data: '0'
					},
				],
				// 基础功能列表
				funList: [{
						icon: 'icon-icon-notice',
						title: '消息通知'
					},
					{
						icon: 'icon-icon-star',
						title: '收藏'
					},
					{
						icon: 'icon-icon-collection',
						title: '点赞'
					},
					{
						icon: 'icon-icon-pie',
						title: '浏览历史'
					}
				],
				// 我的服务
				allFunList:[
					{
						title:'个人管理',
						funList:[
							{
								img:'https://cdn.uviewui.com/uview/album/1.jpg',
								title:'学生认证'
							},
							{
								img:'https://cdn.uviewui.com/uview/album/1.jpg',
								title:'跑腿认证'
							},
							{
								img:'https://cdn.uviewui.com/uview/album/1.jpg',
								url:'/pages/user/site',
								title:'地址管理'
							},
							{
								img:'https://cdn.uviewui.com/uview/album/1.jpg',
								title:'地址管理'
							}
						],
					},
					{
						title:'推荐服务',
						funList:[
							{
								img:'https://cdn.uviewui.com/uview/album/1.jpg',
								title:'学生认证'
							},
							{
								img:'https://cdn.uviewui.com/uview/album/1.jpg',
								title:'跑腿认证'
							},
							{
								img:'https://cdn.uviewui.com/uview/album/1.jpg',
								title:'地址管理'
							},
							{
								img:'https://cdn.uviewui.com/uview/album/1.jpg',
								title:'地址管理'
							},
							{
								img:'https://cdn.uviewui.com/uview/album/1.jpg',
								title:'XXXXX'
							},
							{
								img:'https://cdn.uviewui.com/uview/album/1.jpg',
								title:'XXXXX'
							}
							
						],
					}
				],
				
				
				
				//底部
				tabbar: {
					value: 4,
				},
			}
		},
		onLoad() {
			uni.hideTabBar({})
		},
		onShow() {	
			var self=this
			if(uni.getStorageSync('userAccount')==''||uni.getStorageSync("token")==''){
				this.isLogin=false
				for(var item in self.infoNav){
					self.infoNav[item].data='-'
				}
				uni.reLaunch({
					url:'/pages/user/login'
				})
			}else{
				this.isLogin=true
				// 获取登录信息
				this.$request({
					url:'/user/getUserInfo?userAccount='+uni.getStorageSync('userAccount'),
				}).then(res=>{
					if(res.code==200){
						self.userInfo=res.data
					}
				})
			}
		},
		methods: {
			goPage(item){
				console.log(item)
				uni.navigateTo({
					url:item.url
				})
			},
			login(){
				uni.navigateTo({
					url:'/pages/user/login'
				})
			},
			goSetting(){
				uni.navigateTo({
					url:'/pages/user/setting'
				})
			},
			goSelfHome(){
				uni.navigateTo({
					url:'/pages/user/userHome?userAccount='+this.userInfo.userAccount
				})
			}
		}
	}
</script>

<style lang="scss">
	.navBox {
		width: 100%;
		box-sizing: border-box;
		height: calc(500rpx + var(--status-bar-height));
		padding-top: calc(var(--status-bar-height) + 20rpx);
		padding-left: 30rpx;
		padding-right: 30rpx;
		// background-color: #FFFFFF;
		background-image: linear-gradient(to top, rgba(242, 232, 217, 1.0) 0rpx, #FFFFFF 50rpx);

		// position: fixed;
		//使用sticky不用占位
		position: sticky;
		top: 0;
		left: 0;
		z-index: 1;

		.naviIcon {
			text-align: right;
			height: 50rpx;
			margin-bottom: 16rpx;

			.iconfont {
				font-size: 50rpx;
				color: rgb(110, 110, 110);
			}
		}
		.loginBtnBox{
			height: 150rpx;
			padding: 0 120rpx;
			padding-top: 60rpx;
			box-sizing: border-box;
		}

		.userInfoBox {
			height: 150rpx;
			padding-top: 10rpx;
			padding-left: 16rpx;
			box-sizing: border-box;

			.userAvatar {
				width: 130rpx;
				height: 100%;
				float: left;
				margin-right: 22rpx;
			}

			.userInfo {
				float: left;
				height: 100%;
				width: 50%;
			}

			.tagsBox {
				// width: 30rpx;
				// margin-right: 30rpx;
				margin-top: 6rpx;
				display: flex;
				flex-direction: row;
				flex-wrap: wrap;
				align-items: center;

				.tagsItem {
					flex-direction: column;
					margin-right: 10rpx;

					.u-tag--mini {
						height: 30rpx;

						span {
							font-size: 22rpx;
						}
					}

					.tags {}
				}
			}

			.userIndexBox {
				height: 100%;
				line-height: 150rpx;
				text-align: right;
			}
		}

		.userInfoNav {
			margin-top: 40rpx;

			.infoNavItem {
				padding: 0 10rpx;
				box-sizing: border-box;
				font-size: 28rpx;

				// border-right: 1px rgb(220, 220, 220) solid;
				.infoNavaData {
					font-size: 36rpx;
					font-weight: bold;

				}
			}

			.infoNavItem:last-child {
				border: none;
			}

		}

		.Cer_Man_Model {
			position: absolute;
			height: 110rpx;
			width: 90%;
			box-sizing: border-box;
			// background-color: black;
			bottom: 0;
			left: 5%;
			border-radius: 16rpx 16rpx 0 0;
			border: 6rpx solid #F99104;
			background-color: rgba(254, 244, 229, 1.0);
			border-bottom: none;

			.Cer_Man_Model_Item {}
		}

	}

	.funBox {
		background-color: #FFFFFF;
		// margin: 0 16rpx;
		// margin-top: 16rpx;
		// border-radius: 16rpx;
		padding: 32rpx 16rpx;

		.iconfont {
			color: #F99104;
			font-size: 50rpx;
		}

		.text {
			font-size: 28rpx;
		}
	}

	.allFunBox{
		background-color: #FFFFFF;
		padding: 0 16rpx;
		.funCard{
			padding-bottom: 16rpx;
			.funTitle{
				padding: 0 16rpx;
				margin-bottom: 16rpx;
				font-weight: bolder;
				font-size: 30rpx;
				height: 60rpx;
				line-height: 60rpx;
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
		
		
	}
</style>
