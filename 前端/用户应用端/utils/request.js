var BASE_URL = 'http://192.168.137.1:8081';    //不是h5默认这个地址
// #ifdef H5
BASE_URL = '/api'; 	    //H5下将地址修改为/api
// #endif
 
export default (options)=>{
	uni.showLoading({
		title:'加载中...'
	})
	return new Promise((resolve,reject) =>{
		uni.request({
			url:BASE_URL + options.url,
			method:options.method || 'GET',
			data:options.data || {},
			header:options.header||{},
			success(res) {
				if(res){
					resolve(res.data)
				}
			},
			fail(err) {
				reject(err)
			},
			complete() {
				uni.hideLoading()
			}
		})
	})
}