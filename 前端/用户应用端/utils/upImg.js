var BASE_URL = 'http://192.168.137.1:8081';    //不是h5默认这个地址
// #ifdef H5
BASE_URL = '/api';    //H5下将地址修改为/api
// #endif
 
export default (options)=>{
	uni.showLoading({
		title:'上传中...'
	})
	return new Promise((resolve,reject) =>{
		uni.uploadFile({
			url: BASE_URL+'/file/upImg',
			filePath: options,
			name: 'picture',
			formData: {
				user: 'test'
			},
			success: (res) => {
				resolve(JSON.parse(res.data).msg)
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