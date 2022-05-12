import React, { useState } from "react";
import { Container} from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import {useNavigate} from "react-router-dom";
import axios from 'axios';

let _http = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'content-type': 'application/json;charset=utf-8'
    },
    withCredentials: true
});

function Login({loginCallback}) {

    var navigate = useNavigate();

    const [membername, setMembername] = useState("");
    const [password, setPassword] = useState("");
    const [membernameError, setMembernameError] = useState("");
    const [passwordError, setpasswordError] = useState("");

    const handleValidation = (event) => {
        let formIsValid = true;

        if (membername === '') {
            setMembernameError("아이디를 입력해주세요.");
            return false;
        } else {
            setMembernameError("");
        }

        if (password === '') {
            setpasswordError(
                "비밀번호를 확인해주세요."
            );
            return false;
        } else {
            setpasswordError("");
        }

        return formIsValid;
    };

    const loginSubmit = () => {

        const memberForm = JSON.stringify({
            membername,
            password
        })

        if(handleValidation()){
            const res = _http.post("/login", memberForm);

            // 로그인 완료
            res.then((result) => {

                if(result.status === 200){

                    localStorage.setItem("login-key",result.headers.authorization);
                    loginCallback(true);
                }
            })

            // 로그인 실패
            res.catch((result) => {
                alert("아이디와 비밀번호가 일치하지않습니다.");
                return;
            })
        }
    };


    return (
        <div>
                <Container className="h-100">
                    <div className="row d-flex justify-content-center align-items-center h-100">
                        <div className="col-md-4">
                            <form id="loginform">
                                <div className="form-group">
                                    <label>아이디</label>
                                    <input
                                        type="email"
                                        className="form-control"
                                        id="username"
                                        name="username"
                                        aria-describedby="emailHelp"
                                        placeholder="아이디를 입력하세요"
                                        onChange={(event) => setMembername(event.target.value)}
                                    />
                                    <small id="emailHelp" className="text-danger form-text">
                                        {membernameError}
                                    </small>
                                </div>
                                <div className="form-group">
                                    <label>비밀번호</label>
                                    <input
                                        type="password"
                                        className="form-control"
                                        id="password"
                                        name="password"
                                        placeholder="비밀번호를 입력하세요"
                                        onChange={(event) => setPassword(event.target.value)}
                                    />
                                    <small id="passworderror" className="text-danger form-text">
                                        {passwordError}
                                    </small>
                                </div>
                                <div className="form-group form-check">
                                    <input
                                        type="checkbox"
                                        className="form-check-input"
                                        id="exampleCheck1"
                                    />
                                    <label className="form-check-label">아이디 저장</label>
                                </div>
                                <button type="button" className="btn btn-primary" onClick={loginSubmit}>
                                    로그인
                                </button>
                                <button type="button" className="btn btn-primary" onClick={() => navigate("/sign")}>
                                    회원가입
                                </button>
                            </form>
                        </div>
                    </div>
                </Container>
        </div>
    );
}

export default Login;