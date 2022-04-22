import React from "react";
import { Button, Container, Navbar, Nav } from "react-bootstrap";
import { Link } from "react-router-dom";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";

function Navigation({ isLogin, loginCallback }) {
    // function logout() {
    //     axios({
    //         url: "/user/logoutUser",
    //         method: "POST",
    //     }).then((res) => {
    //         localStorage.setItem("trip-token", "");
    //         loginCallback(res.data);
    //     });
    // }

    return (
        <Navbar bg='primary' variant='dark'>
            <Container>
                <Navbar.Brand>저리가계</Navbar.Brand>
                    <>
                        <Nav className='me-auto white'>
                            <Link to="/home">
                                <Button>홈</Button>
                            </Link>
                            <Link to="/mypage">
                                <Button>마이페이지</Button>
                            </Link>
                        </Nav>
                        <Link to="/login">
                            <Button variant='secondary'>
                                    로그인
                            </Button>
                        </Link>
                    </>
            </Container>
        </Navbar>
    );
}

export default Navigation;