<template>
	<view class="page">
		<view class="header">
			<view class="backBox" @click="backTo">
				<i class="iconfont icon-31fanhui1" style="color: rgb(160,160,160);"></i>
			</view>
			<view class="headerTitle">
				个人资料设置
			</view>
			<view>
				<view style="color: #0aa0fa;" @click="upInfo">保存</view>
			</view>
		</view>
		<!-- 基本信息 -->
		<view class="infoBox">
			<view class="infoTitle">
				<text>基本信息</text>
			</view>
			<view class="infoAll">
				<u-cell-group :border="false">
					<u-cell :border="false" title="头像" :isLink="true">
						<avatar selWidth="200rpx" selHeight="200rpx" :avatarSrc="tempAvarter" @upload="avatarUpload"
							avatarStyle="width: 100rpx; height: 100rpx;border: 1px solid #000;" inner="true"
							style="margin: 0 auto;width: 100rpx;height: 100rpx;" slot='value'></avatar>
					</u-cell>
					<u-cell :border="false" title="账号">
						<text slot="value">{{userInfo.userAccount}}</text>
					</u-cell>
					<u-cell :border="false" title="昵称" style="padding-right: 22rpx;">
						<u--input placeholder="请输入昵称" border="bottom" v-model="userInfo.userName" inputAlign="right" slot="value"></u--input>
						
						<!-- <text slot="value">{{userInfo.userName}}</text> -->
					</u-cell>
					<u-cell :border="false" title="性别" :isLink="true" @click="sexBoxShow=true">
						<text slot="value">{{userInfo.userGender=='1'?'男':'女'}}</text>
					</u-cell>
					<u-cell :border="false" title="电子邮箱" style="padding-right: 22rpx;">
						<u--input placeholder="请输入电子邮箱" border="bottom" v-model="userInfo.userEmail" inputAlign="right" slot="value"></u--input>
						<!-- <text slot="value">{{userInfo.userEmail!=null?userInfo.userEmail:'设置电子邮箱'}}</text> -->
					</u-cell>
					<u-cell :border="false" title="联系方式" style="padding-right: 22rpx;">
						<u--input placeholder="请输入联系方式" border="bottom" v-model="userInfo.userPhone" inputAlign="right" slot="value"></u--input>
						<!-- <text slot="value">{{userInfo.userPhone!=null?userInfo.userPhone:'设置联系方式'}}</text> -->
					</u-cell>
				</u-cell-group>
			</view>
		</view>
		<!-- <view class="changeBtn">
			<u-button type="primary" :plain="false"  text="修改" color="#0aa0fa" @click="upUserInfo">
			</u-button>
		</view> -->
		<!-- 认证信息 -->
		<view class="infoBox" style="margin-top:16rpx;padding-top: 16rpx;">
			<view class="infoTitle">
				<text>认证信息</text>
			</view>
			<view class="infoAll">
				<u-cell-group :border="false">
					<u-cell :border="false" title="学生认证">
						<view slot="value">
							未认证
						</view>
					</u-cell>
					<u-cell :border="false" title="商家认证">
						<view slot="value">
							未认证
						</view>
					</u-cell>
					<u-cell :border="false" title="兼职认证">
						<view slot="value">
							未认证
						</view>
					</u-cell>
				</u-cell-group>
			</view>
		</view>
		<u-action-sheet :show="sexBoxShow" :actions="sexType" title="请选择性别" @close="sexBoxShow = false"
			@select="sexSelect">
		</u-action-sheet>
		<u-toast ref="uToast"></u-toast>
	</view>
</template>

<script>
	import avatar from "../../components/yq-avatar/yq-avatar.vue";
	export default {
		components: {
			avatar
		},
		data() {
			return {
				tempAvarter: '',
				userInfo: {
					userId: ''
				},
				sexBoxShow: false,
				sexType: [{
						name: '男',
						type: '1'
					},
					{
						name: '女',
						type: '0'
					}
				]
			}
		},
		onShow() {
			var self = this
			this.userInfo.userAccount = uni.getStorageSync('userAccount')
			this.$request({
				url: '/user/getUserInfo?userAccount=' + uni.getStorageSync('userAccount')
			}).then(res => {
				if (res.code == 200) {
					self.userInfo = res.data
					self.tempAvarter = res.data.userPortrait
					console.log(res)
				}
			})

		},
		methods: {
			sexSelect(e) {
				this.userInfo.userGender = e.type
				// this.upInfo()
			},
			avatarUpload(pic) {
				var self = this
				this.tempAvarter = pic.path
				this.$upImg(pic.path).then(res => {
					console.log(res)
					self.userInfo.userPortrait = res
					// self.upInfo()
				})
			},
			//更新数据
			upInfo() {
				if(this.userInfo.userName==''){
					this.$refs.uToast.show({
						message: '昵称不能为空哦',
						position: 'bottom',
						duration: 1000
					})
					return
				}
				var self = this
				this.$request({
					url: '/user/UpUserInfo',
					data: self.userInfo,
					method: 'POST',
					header: {
						token: uni.getStorageSync('token')
					}
				}).then(res => {
					if (res.code == 200) {
						console.log(res)
						self.$refs.uToast.show({
							message: '修改成功',
							duration: 1000,
							complete() {
								uni.navigateBack({
									
								})
							}
						})
					}
				})
			},
			backTo() {
				uni.navigateBack({

				})
			}
		}
	}
</script>

<style lang="scss">
	.header {
		height: calc(var(--status-bar-height) + 100rpx);
		padding: 0 32rpx;
		padding-top: var(--status-bar-height);
		box-sizing: border-box;
		line-height: 100rpx;
		background-color: #FFFFFF;
		display: flex;
		align-items: center;

		.backBox {
			width: 50rpx;
		}

		.headerTitle {
			flex: 1;
			text-align: center;
		}

		.yesBtn {
			height: 50rpx;
			width: 100%;
			border-radius: 50rpx;
			background-color: #0aa0fa;
		}
	}

	.infoBox {
		background-color: #FFFFFF;

		.infoTitle {
			padding: 0 22rpx;
			font-size: 28rpx;
			color: rgb(200, 200, 200);
		}
	}

	.changeBtn {
		margin-top: 16rpx;
		padding: 0 22rpx;

	}
</style>
