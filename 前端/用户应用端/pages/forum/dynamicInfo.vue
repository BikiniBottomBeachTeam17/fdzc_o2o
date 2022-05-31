<template>
	<view class="page">
		<view class="headerBox">
			<view class="headerTitle">
				<view class="backBox" @click="backTo">
					<i class="iconfont icon-31fanhui1"></i>
				</view>
				<view class="titleBox">
					动态详情
				</view>
				<view class="rightBox"></view>
			</view>
			
		</view>
		<view class="content">
			<dynamicCard :dyInfo="dynamicInfo" :isInfo="true" :lines="lines"></dynamicCard>
		</view>
		<view class="commentBox">
			<view class="commentTitle">
				评论
			</view>
			<view class="commentItem" v-for="(item,index) in dynamicInfo.comList" :key="index">
				<!-- 评论顶部 -->
				<view class="commentHead">
					<!-- 评论者信息 -->
					<view class="user">
						<!-- 头像 -->
						<view class="userAvatar">
							<u-avatar :src="item.userPortrait" size="80rpx"></u-avatar>
						</view>
						<!-- 信息 -->
						<view class="userInfo">
							<view class="name">{{item.userName}}</view>
							<view class="time">{{item.pushTime}}</view>
						</view>
					</view>
				</view>
				<!-- 评论内容 -->
				<view class="comContent">
					<u--text mode="text" :lines="lines" :text="item.content" size="30rpx"></u--text>
				</view>
			</view>
		</view>
		<!-- 底部，回复 -->
		<view class="replyBox">
			<view class="default" v-if="!commentShow">
				<!-- 输入框 -->
				<view class="inputBtn">
					觉得好玩说两句...
				</view>
			</view>
			<view class="inputShow" v-if="commentShow">
				<u-textarea v-model="comment" border="none" maxlength="-1" height="150" ref="textarea"
					:keyboardheightchange="test"></u-textarea>
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
				comment: '',
				lines: 0,
				dynamicInfo: {
					id: 2,
					userPortrait: '',
					userName: '木',
					pushTime: '',
					content: '',
					dyImgs: [],
					comment: 0,
					like: 0,
					comList: []
				},
				commentShow: false
			}
		},
		onLoad(option) {
			var self = this
			this.$request({
				url: '/forum/getForumById?id=' + option.id
			}).then(res => {
				console.log(res)
				self.dynamicInfo = res.data
			})
		},
		methods: {
			backTo() {
				uni.navigateBack({})
			},
			test() {
				this.commentShow = true
			}
		}
	}
</script>

<style lang="scss">
	.headerBox {
		height: calc(100rpx + var(--status-bar-height));
		width: 100%;
		padding-top: var(--status-bar-height);
		box-sizing: border-box;
		background-color: #FFFFFF;

		.headerTitle {
			display: flex;
			flex-direction: row;
			justify-content: space-between;
			height: 100rpx;
			line-height: 100rpx;
			text-align: center;
			padding: 0 18rpx;


			.backBox,
			.rightBox {
				width: 25px;
			}
		}
	}

	.content {
		background-color: #FFFFFF;
		padding-top: 8rpx;
		width: 100%;
	}

	.commentBox {
		background-color: #FFFFFF;

		.commentTitle {
			// background-color: red;
			padding: 0 22rpx;
			box-sizing: border-box;
			height: 80rpx;
			line-height: 80rpx;
			border-bottom: 1rpx solid rgb(245, 245, 245);
		}

		.commentItem {
			background-color: #FFFFFF;
			padding: 0 16rpx;
			padding-bottom: 20rpx;
			border-bottom: 1rpx solid rgb(248, 248, 248);

			.commentHead {
				display: flex;
				flex-direction: row;
				justify-content: space-between;
				height: 80rpx;
				padding: 16rpx 0;
				padding-left: 10rpx;

				.user {
					display: flex;
					flex-direction: row;
					justify-content: flex-start;
					align-items: center;

					.userAvatar {
						margin-right: 10rpx;
					}

					.userInfo {
						.name {
							color: #6e6e6e;
							font-size: 28rpx;
						}

						.time {
							color: #bebebe;
							font-size: 24rpx;
						}
					}
				}
			}
		}

		.comContent {
			padding-left: 100rpx;
			padding-right: 20rpx;
			box-sizing: border-box;
		}
	}

	.replyBox {
		width: 100%;
		background-color: #FFFFFF;
		position: fixed;
		left: 0;
		bottom: 0;

		.default {
			width: 100%;
			height: 100rpx;
			padding: 16rpx 10rpx;
			box-sizing: border-box;

			.inputBtn {
				width: 100%;
				height: 100%;
				background-color: rgb(245, 245, 245);
				border-radius: 100rpx;
				line-height: 68rpx;
				color: rgb(160, 160, 160);
				padding-left: 32rpx;
				box-sizing: border-box;
			}
		}

		.inputShow {
			width: 100%;
			height: 200rpx;
		}
	}
</style>
