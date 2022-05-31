<template>
	<view class="page">
		<!-- 顶部占位符 -->
		<view class="gapBox"></view>
		<view class="headerBox">
			<!-- 顶部导航 -->
			<view class="headerTitle">
				<u--text text="发现" size="36rpx"></u--text>
			</view>
			<!-- 搜索栏 -->
			<view class="searchBox">
				<view class="search">
					搜索话题/帖子/用户
				</view>
			</view>
		</view>
		<view class="tabsBox">
			<u-tabs :list="square" @click="squareClick"  lineWidth="50rpx" lineHeight="8rpx"
				lineColor="#000000" 
				:activeStyle="{
					fontSize:'30rpx',
					color:'#000000',
					fontWeight: 'bold',
					transform: 'scale(1.05)'
				}" :inactiveStyle="{
					fontSize:'30rpx',
				    color: '#606266',
				    transform: 'scale(1)'
				}" :itemStyle="{
					height:'100rpx',
					width:'120rpx'
				}"></u-tabs>
				<view class="addDy" @click="addForum">
					发动态
				</view>
		</view>
		<view class="dynamicBox">
			<block v-for="(item,index) in dyList">
				<dynamicCard :dyInfo="item" :isInfo="false" :lines="4"></dynamicCard>
			</block>
		</view>
		<tabbar :value="1"></tabbar>
	</view>
</template>

<script>
	import tabbar from '../../components/public/tabbar.vue'
	import dynamicCard from '../../components/forum/dynamicCard.vue'
	export default {
		components: {
			tabbar,
			dynamicCard
		},
		data() {
			return {
				pageNum:1,
				pageSize:10,
				square: [{
						name: '动态广场'
					},
					{
						name: '关注'
					}
				],
				dyList: [{
						id: 0,
						type: 1,
						userPortrait: '',
						userName: '',
						pushTime: '',
						content: '',
						dyImgs: [],
						comment:0,
						like:0
					}
				],
				scrollTop:0
			}
		},
		onLoad() {
			uni.hideTabBar({})
			this.$request({
				url:'/forum/getAllForum?pageNum='+this.pageNum+'&pageSize='+this.pageSize
			}).then(res=>{
				console.log(res.data.forumList)
				this.dyList=res.data.forumList
			})
		},
		onPullDownRefresh(){
			this.$request({
				url:'/forum/getAllForum?pageNum='+this.pageNum+'&pageSize='+this.pageSize
			}).then(res=>{
				console.log(res.data.forumList)
				this.dyList=res.data.forumList
			})
		},
		methods: {
			squareClick(e) {
				console.log(e)
			},
			addForum(){
				uni.navigateTo({
					url:'/pages/forum/addForum'
				})
			}
		}
	}
</script>

<style lang="scss">
	.gapBox {
		z-index: 1;
		width: 100%;
		height: var(--status-bar-height);
		background-color: #FFFFFF;
		position: fixed;
		top: 0;
		left: 0;
	}

	.headerBox {
		background-color: #FFFFFF;
		padding:0 32rpx;
		
		.headerTitle{
			height: 100rpx;
			margin-top:var(--status-bar-height) ;
			// background-color: red;
			line-height: 100rpx;
		}
		.searchBox{
			height: 80rpx;
			.search{
				background-color: rgb(235, 235, 235);
				height: 80rpx;
				border-radius: 40rpx;
				line-height: 80rpx;
				text-align: center;
				color: rgb(180, 180, 180);
			}
		}
	}

	.tabsBox {
		height: 100rpx;
		background-color: #FFFFFF;
		border-bottom: 1px solid rgb(245, 245, 245);
		position: sticky;
		top: var(--status-bar-height);
		left: 0;
		z-index: 1;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		.addDy{
			height: 100rpx;
			line-height: 100rpx;
			margin-right: 30rpx;
			font-size: 30rpx;
		}
	}
</style>
