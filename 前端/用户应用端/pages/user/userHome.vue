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
			<view class="userInfoHeard">
				<!-- 用户头像 -->
				<view class="userAvatar">
					<u-avatar :src="userInfo.userPortrait" size="170rpx"></u-avatar>
				</view>
				<!-- 用户信息 -->
				<view>
					
				</view>
				<view class="userBtn">
					<!-- <u-button text="关注" color="#0aa0fa" shape="circle"></u-button> -->
				</view>
			</view>
			<view class="uerMoreInfo">
				{{userInfo.userName}}
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
		components:{
			dynamicCard
		},
		data(){
			return{
				// 是否为本人
				isSelf:false,
				userInfo:{
					userPortrait:''
				},
				dyList:[],
				funList:[
					{
						name:'帖子'
					},
					{
						name:'宝贝'
					}
				],
				funNum:0
			}
		},
		onLoad(option) {
			console.log(option.userAccount)
			this.$request({
				url:'/forum/getForumByUserId?userAccount='+option.userAccount,
			}).then(res=>{
				console.log(res)
				this.dyList=res.data.forumList
			})
			this.$request({
				url:'/user/getUserInfo?userAccount='+option.userAccount
			}).then(res=>{
				console.log(res)
				this.userInfo=res.data
			})
		},
		methods:{
			backTo(){
				uni.navigateBack({})
			},
			funChange(e){
				console.log(e)
				this.funNum = e.index
			}
		}
	}
</script>

<style lang="scss">
	.page{
		// background-color: #FFFFFF;
		// background-image: url('C:\\Users\\chen\\Desktop\\1.jpg');
		background-size:100%;
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
	.userInfo{
		height: 428rpx;
		border-bottom:1rpx solid rgb(240,240,240) ;
		background-color: #FFFFFF;
		.userInfoHeard{
			height: 130rpx;
			margin: 0 24rpx;
			// background-color: rgba(200,200,200,0.5);
			position: relative;
			.userAvatar{
				position: absolute;
				left: 0rpx;
				top: -60rpx;
				border-radius: 50%;
				border: 6rpx solid #FFFFFF;
			}
		}
	}
	.dataChoseeBox{
		background-color: #FFFFFF;
		height: 90rpx;
	}
	.forumData{
		.forumheader{
			padding: 10rpx 28rpx;
		}
	}
</style>
