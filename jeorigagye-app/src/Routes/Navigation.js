import React from "react";
import { Button, Container, Navbar, Nav } from "react-bootstrap";
import { Link, Outlet, useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from "axios";

let _http = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'content-type': 'application/json;charset=utf-8'
    },
    withCredentials: true
});

function Navigation({ loginCallback }) {

    var navigate = useNavigate();

    const logout = () => {

        // let res = _http.post("/member/logout");
        //
        // res.then((result) => {
        //     if(result.status  === 200){
                localStorage.removeItem("login-key");
                loginCallback(false);
                navigate("/");
        //     }
        // })
    }

    return (
        <div>
            <Navbar bg='primary' variant='dark'>
                <Container>
                    <Navbar.Brand>저리가계</Navbar.Brand>
                        <>
                            <Nav className='me-auto white'>
                                <Link to="/">
                                    <Button>홈</Button>
                                </Link>
                                <Link to="/">
                                    <Button>입출금내역</Button>
                                </Link>
                                <Link to="/">
                                    <Button>정기지출</Button>
                                </Link>
                                <Link to="/mypage">
                                    <Button>마이페이지</Button>
                                </Link>
                            </Nav>
                            <Button variant='secondary' onClick={() => logout()}>
                                    로그아웃
                            </Button>
                        </>
                </Container>
            </Navbar>
            <Outlet/>
        </div>
    );
}

export default Navigation;