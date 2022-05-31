<template>
	<!-- 用户主页 -->
	<view class="page">
		<view class="header">
			<view class="backBox" @click="backTo">
				<i class="iconfont icon-31fanhui1" style="color: #FFFFFF;"></i>
			</view>
			<view class="registerBox">
				<u-icon name="more-dot-fill" color="#FFFFFF" size="36"></u-icon>
			</view>
		</view>
		<view class="userInfo">
			<view class="userInfoHeard" style="display: flex;align-items: center;">
				<!-- 用户头像 -->
				<view class="userAvatar">
					<u-avatar :src="userInfo.userPortrait" size="170rpx"></u-avatar>
				</view>
				<!-- 用户信息 -->
				<view style="margin-left: 180rpx;width: 200rpx;" class="userData">
					<u-grid :border="false" :col="2">
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
				<view style="flex: 1;"></view>
				<view class="userBtn" style="width: 200rpx;">
					<view class="btnBox" v-if="!isSelf">关注</view>
					<view class="btnBox selfBtn" v-if="isSelf" @click="goUserSet">编辑资料</view>
				</view>
			</view>
			<view class="uerMoreInfo">
				<view class="user">
					<u--text :text="userInfo.userName" size="40" :bold="true"></u--text>
					<u--text text="信用分:100" size="22" color="rgb(160,160,160)"></u--text>
				</view>
			</view>
		</view>
		<!-- 数据信息 -->
		<view class="dataChoseeBox">
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
		<!-- 帖子信息 -->
		<view class="forumData" v-if="funNum==0">
			<view class="forumheader">
				<u--text :text="'全部帖子'" size="28" color="rgb(160,160,160)"></u--text>
			</view>
			<view class="forumItem">
				<block v-for="(item,index) in dyList">
					<dynamicCard :dyInfo="item" :isInfo="false" :lines="4"></dynamicCard>
				</block>
			</view>
		</view>

	</view>
</template>

<script>
	import dynamicCard from '../../components/forum/dynamicCard.vue'
	export default {
		components: {
			dynamicCard
		},
		data() {
			return {
				// 是否为本人
				isSelf: false,
				userInfo: {
					userPortrait: ''
				},
				dyList: [],
				funList: [{
						name: '帖子'
					},
					{
						name: '宝贝'
					}
				],
				funNum: 0,
				// 用户基础数据
				infoNav: [{
						title: '关注',
						data: '0'
					},
					{
						title: '粉丝',
						data: '0'
					},
				],
			}
		},
		onLoad(option) {
			console.log(option.userAccount)
			if (option.userAccount == uni.getStorageSync('userAccount')) {
				this.isSelf = true
			} else {
				this.isSelf = false
			}
			this.$request({
				url: '/forum/getForumByUserId?userAccount=' + option.userAccount,
			}).then(res => {
				console.log(res)
				this.dyList = res.data.forumList
			})
			this.$request({
				url: '/user/getUserInfo?userAccount=' + option.userAccount
			}).then(res => {
				console.log(res)
				this.userInfo = res.data
			})
		},
		methods: {
			backTo() {
				uni.navigateBack({})
			},
			funChange(e) {
				console.log(e)
				this.funNum = e.index
			},
			goUserSet(){
				uni.navigateTo({
					url:'/pages/user/userInfoSetting'
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.page {
		// background-color: #FFFFFF;
		// background-image: url('C:\\Users\\chen\\Desktop\\1.jpg');
		background-size: 100%;
		background-position: 0 -25px;
	}

	.header {
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

	.userInfo {
		height: 428rpx;
		border-bottom: 1rpx solid rgb(240, 240, 240);
		background-color: #FFFFFF;

		.userInfoHeard {
			height: 100rpx;
			margin: 0 24rpx;
			margin-bottom: 30rpx;
			// background-color: rgba(200,200,200,0.5);
			position: relative;

			.userAvatar {
				position: absolute;
				left: 0rpx;
				top: -60rpx;
				border-radius: 50%;
				border: 6rpx solid #FFFFFF;
			}

			.userData {
				.infoNavaData {
					font-size: 30rpx;
					font-weight: bold;
				}

				.infoNavaTitle {
					font-size: 24rpx;
					color: rgb(160, 160, 160);
				}
			}

			.btnBox {
				width: 200rpx;
				height: 60rpx;
				line-height: 60rpx;
				text-align: center;
				font-size: 30rpx;
				font-weight: bolder;
				color: #FFFFFF;
				border-radius: 60rpx;
				background-color: #0aa0fa;
			}
			.selfBtn{
				background-color: rgb(240,240,240);
				color: #000000;
			}
		}

		.uerMoreInfo {
			padding-left: 44rpx;
		}
	}

	.dataChoseeBox {
		background-color: #FFFFFF;
		height: 90rpx;
	}

	.forumData {
		.forumheader {
			padding: 10rpx 28rpx;
		}
	}
</style>
