import React, {useEffect} from "react";
import axios from "axios";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Navigation from "./Navigation";
import Main from "./Main";
import MyPage from "./MyPage";
import LayOut from "./LayOut";
import Login from "./Login";
import Sign from "./Sign";
import {useObserver} from "mobx-react";
import indexStore from "../modules/indexStore";

let _http = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'content-type': 'application/json;charset=utf-8',
        'Authorization': localStorage.getItem('login-key')
    },
    withCredentials: true
});

function Router(){

    const {loginStore} = indexStore();

    useEffect(() => {
        console.log("Router.js Check")
    })

    return(
        <>
            <BrowserRouter>
                <Routes>
                    {loginStore.isLogin ?
                        <Route path="/" element={<Navigation/>}>
                            <Route path="/" element={<Main/>} />
                            <Route path="/mypage" element={<MyPage/>} />
                        </Route>
                        :
                        <Route path="/" element={<LayOut/>}>
                            <Route path="/" element={<Login/>}/>
                            <Route path="/sign" element={<Sign/>} />
                        </Route>
                    }
                </Routes>
            </BrowserRouter>
        </>
    );
}

export default Router;