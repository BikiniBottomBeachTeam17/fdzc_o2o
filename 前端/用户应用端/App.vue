<script>
	export default {
		onLaunch: function() {
			var self=this
			console.log('App Launch')
			uni.hideTabBar({})
			if(uni.getStorageSync("userAccount")&&uni.getStorageSync("token")){
				self.$request({
					url:'/user/upToken',
					method:'POST',
					header:{
						token:uni.getStorageSync("token")
					}
				}).then(res=>{
					console.log(res)
					if(res.code!=-1){
						uni.setStorageSync("userAccount",res.data.userAccount)
						uni.setStorageSync("token",res.data.token)
					}else{
						uni.removeStorageSync("userAccount")
						uni.removeStorageSync("token")
					}
				})
			}
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style>
	/*每个页面公共css */
	@import url("./static/iconfont/iconfont.css");

	.page {
		width: 100%;
		height: 100%;
		background-color: rgb(240, 240, 240);
		position: absolute;
		overflow-y: auto;

		::-webkit-scrollbar {
			width: 0;
		}
	}
</style>
