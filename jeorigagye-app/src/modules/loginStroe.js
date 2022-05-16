import {observable} from 'mobx';
import axios from "axios";

let _http = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'content-type': 'application/json;charset=utf-8',
        'Authorization': localStorage.getItem('login-key')
    },
    withCredentials: true
});

const loginStore = observable({

    // state
    isLogin: false,

    loginCallback(isLogin){
        this.isLogin = isLogin;
    },

    loginCheck (){
        const res = _http.get("/member/check");

        res.then((result) => {

            if(result.status === 200){
                if(result.data){
                    console.log("true")
                    this.isLogin = true;
                }else{
                    console.log("false")
                    this.isLogin = false;
                }
            }
        })
    }

})

export default loginStore;
