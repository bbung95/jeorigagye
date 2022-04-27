import React from "react";
import { Button, Container, Navbar, Nav } from "react-bootstrap";
import { Link, Outlet, useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

function Navigation({ loginCallback }) {

    const navigate = useNavigate();

    const logout = () => {
        // axios({
        //     url: "/user/logoutUser",
        //     method: "POST",
        // }).then((res) => {
        //     localStorage.setItem("trip-token", "");
        //     loginCallback(res.data);
        // });

        loginCallback(false);
        navigate("/");
    }

    return (
        <div>
            <Navbar bg='primary' variant='dark'>
                <Container>
                    <Navbar.Brand>저리가계</Navbar.Brand>
                        <>
                            <Nav className='me-auto white'>
                                <Link to="/main">
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