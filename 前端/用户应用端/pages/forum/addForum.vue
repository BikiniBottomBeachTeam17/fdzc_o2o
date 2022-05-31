<template>
	<view>
		<view class="header">
			<view class="backBox" @click="backTo">
				<i class="iconfont icon-31fanhui1" style="color: rgb(160,160,160);"></i>
			</view>
			<view class="registerTitle" style="flex: 1;">

			</view>
			<view style="width: 150rpx;">
				<!-- <u-button text="发布" shape="circle" type="primary" color="#0aa0fa"></u-button> -->
				<view style="height: 60rpx;background-color:#0aa0fa ;line-height: 60rpx;text-align: center;color: #FFFFFF;border-radius: 30rpx;" @click="addForum">
					发布
				</view>
			</view>
		</view>
		<view class="titleBox">
			<u--textarea v-model="content" border="none" placeholder="说点好玩的" height="500rpx"></u--textarea>
		</view>
		<view class="unloadImg">
			<u-upload :fileList="imgList" @afterRead="afterRead" @delete="deletePic" name="1" multiple :maxCount="9" width="200" height="200">
			</u-upload>
		</view>
		<u-toast ref="uToast"></u-toast>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				imgList: [],
				content: '',
				uploadImg:[]
			}
		},
		methods: {
			backTo(){
				uni.navigateBack({
					
				})
			},
			// 删除图片
			deletePic(event) {
				this.imgList.splice(event.index, 1)
				this.uploadImg.splice(event.index,1)
			},
			// 新增图片
			async afterRead(event) {
				// 当设置 mutiple 为 true 时, file 为数组格式，否则为对象格式
				let lists = [].concat(event.file)
				let fileListLen = this.imgList.length
				lists.map((item) => {
					this.imgList.push({
						...item,
						status: 'uploading',
						message: '上传中'
					})
				})
				for (let i = 0; i < lists.length; i++) {
					const result = await this.uploadFilePromise(lists[i].url)
					let item = this.imgList[fileListLen]
					this.imgList.splice(fileListLen, 1, Object.assign(item, {
						status: 'success',
						message: '',
						url: result
					}))
					fileListLen++
				}
			},
			uploadFilePromise(url) {
				this.$upImg(url).then(res=>{
					console.log(res)
					this.uploadImg.push(res)
				})
				console.log(this.uploadImg)
			},
			addForum(){
				var self=this
				this.$request({
					url:'/forum/addForum',
					method:'POST',
					data:{
						content:self.content,
						imgList:self.uploadImg
					},
					header:{
						token:uni.getStorageSync('token')
					}
				}).then(res=>{
					if(res.code==200){
						self.$refs.uToast.show({
							message: '发布成功',
							position: 'bottom',
							duration: 1000,
							complete() {
								uni.navigateBack({})
							}
						})
					}
				})
			}
		}
	}
</script>

<style lang="scss">
	.header {
		height: calc(var(--status-bar-height) + 100rpx);
		padding: 0 16rpx;
		padding-top: 16rpx;
		line-height: 100rpx;
		display: flex;
		align-items: center;
	}
	.titleBox{
		
	}
	.unloadImg{
		width: 680rpx;
		margin: 0 auto;
		margin-top: 16rpx;
	}
</style>
