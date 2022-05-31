<template>
	<view>
		<view class="dynamicBox" @click.stop="isInfo?'':goInfo(dyInfo.id)">
			<!-- 顶部用户信息 -->
			<view class="header">
				<!-- 用户 -->
				<view class="user">
					<!-- 用户头像 -->
					<view class="userAvatar" @tap.stop="goUserInfo(dyInfo.authorAccount)">
						<u-avatar :src="dyInfo.userPortrait" size="80rpx"></u-avatar>
					</view>
					<!-- 用户信息 -->
					<view class="userInfo">
						<view class="name">{{dyInfo.userName}}</view>
						<view class="time">{{dyInfo.pushTime}}</view>
					</view>
				</view>
				<!-- 关注 -->
				<view class="operate">
					<view class="operateBtn" v-if="isInfo&&(isSelf?false:true)">关注</view>
					<!-- <view class="editBtn" v-if="isInfo&&isSelf">编辑</view> -->
				</view>
			</view>
			<!-- 数据 -->
			<view class="contentBox">
				<!-- 文本 -->
				<view class="content">
					<u--text mode="text" :lines="lines" :text="dyInfo.content" size="30rpx"></u--text>
				</view>
				<!-- 图片 -->
				<view class="imgBox" style="padding: 20rpx 0;">
					<!-- 单张 -->
					<block v-if="dyInfo.dyImgs.length == 1">
						<view class="img1Box">
							<image @tap.stop="previewImage(img, dyInfo.dyImgs)" :src="img" mode="aspectFill"
								v-for="(img,index1) in dyInfo.dyImgs" :key="index1" class="img1"></image>
						</view>
					</block>
					<!-- 2张 -->
					<block v-if="dyInfo.dyImgs.length == 2">
						<view class="img2Box">
							<image @tap.stop="previewImage(img, dyInfo.dyImgs)" :src="img" mode="aspectFill"
								v-for="(img,index2) in dyInfo.dyImgs" :key="index2" class="img2"></image>
						</view>
					</block>
					<!-- 4张 -->
					<block v-if="dyInfo.dyImgs.length == 4">
						<view class="img4Box">
							<image @tap.stop="previewImage(img, dyInfo.dyImgs)" :src="img" mode="aspectFill"
								v-for="(img,index3) in dyInfo.dyImgs" :key="index3" class="img4"></image>
						</view>
					</block>
					<!-- 3张以上 -->
					<block v-if="dyInfo.dyImgs.length > 4||dyInfo.dyImgs.length==3">
						<view class="imgBox">
							<image @tap.stop="previewImage(img, dyInfo.dyImgs)" :src="img" mode="aspectFill"
								v-for="(img,index4) in dyInfo.dyImgs" :key="index4" class="img"></image>
						</view>
					</block>
				</view>
			</view>
			<!-- 评论与点赞 -->
			<view class="operateFootBox">
				<view class="commentBox">
					<i class="iconfont icon-icon-message2"></i>
					<u--text :text="dyInfo.comment==0?'评论':dyInfo.comment" size="26rpx" color="#a0a0a0"></u--text>
				</view>
				<view class="likeBox">
					<i class="iconfont icon-icon-collection"></i>
					<u--text :text="dyInfo.like==0?'赞':dyInfo.like" size="26rpx" color="#a0a0a0"></u--text>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			dyInfo: {},
			lines: 0,
			isInfo: {
				type: Boolean
			},


		},
		data() {
			return {
				isSelf: false
			}
		},
		beforeUpdate() {
			if (this.dyInfo.authorAccount == uni.getStorageSync('userAccount')) {
				this.isSelf = true
			}
		},
		methods: {
			previewImage(url, urls) {
				uni.previewImage({
					current: url, // 当前显示图片的http链接
					urls: urls // 需要预览的图片http链接列表
				});
			},
			goInfo(id) {
				console.log(id)
				uni.navigateTo({
					url: '/pages/forum/dynamicInfo?id=' + id
				})
			},
			goUserInfo(id) {
				uni.navigateTo({
					url: '/pages/user/userHome?userAccount=' + id
				})

			}
		}
	}
</script>

<style lang="scss" scoped>
	.dynamicBox {
		background-color: #FFFFFF;
		padding: 0 16rpx;
		padding-bottom: 20rpx;
		margin-bottom: 16rpx;
	}

	.header {
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

		.operate {
			margin-top: 10rpx;
			margin-right: 20rpx;

			.operateBtn {
				width: 120rpx;
				height: 60rpx;
				line-height: 60rpx;
				font-size: 30rpx;
				text-align: center;
				color: #FFFFFF;
				font-weight: bold;
				border-radius: 30rpx;
				background-color: #0aa0fa;
			}
			.editBtn{
				width: 120rpx;
				height: 60rpx;
				line-height: 60rpx;
				font-size: 30rpx;
				text-align: center;
				color: #0aa0fa;
				font-weight: bold;
				border-radius: 30rpx;
				background-color: #FFFFFF;
				border: 1rpx solid #0aa0fa;
			}
		}
	}

	.contentBox {
		padding-left: 100rpx;
		padding-right: 20rpx;
		box-sizing: border-box;

		.comtent {}

		.imgBox {
			.img1Box {
				display: flex;
				flex-direction: row;
				width: 250rpx;

				.img1 {
					border-radius: 16rpx;
					width: 100%;
					height: 250rpx;
				}
			}

			.img2Box {
				display: flex;
				flex-direction: row;
				width: 400rpx;

				.img2 {
					margin: 0 5rpx;
					border-radius: 16rpx;
					width: 50%;
					height: 200rpx;
				}
			}

			.img4Box {
				display: flex;
				flex-direction: row;
				flex-wrap: wrap;
				width: 420rpx;

				.img4 {
					width: 48%;
					margin: 0 5rpx;
					height: 200rpx;
					border-radius: 16rpx;
				}

				.img4:nth-child(1),
				.img4:nth-child(2) {
					margin-bottom: 10rpx;
				}
			}

			.imgBox {
				display: flex;
				flex-direction: row;
				flex-wrap: wrap;

				.img {
					width: 30%;
					margin: 0 5rpx;
					margin-bottom: 10rpx;
					height: 200rpx;
					border-radius: 16rpx;
				}
			}
		}

	}

	.operateFootBox {
		padding-left: 100rpx;
		display: flex;
		flex-direction: row;

		.commentBox,
		.likeBox {
			display: flex;
			flex-direction: row;
			justify-content: flex-start;
			align-items: center;
			font-size: 28rpx;

			.iconfont {
				font-size: 46rpx;
				color: rgb(160, 160, 160);
				margin-right: 10rpx;
			}
		}

		.commentBox {
			margin-right: 60rpx;
		}

	}
</style>


