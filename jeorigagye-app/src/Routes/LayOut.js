import React from "react";
import { Link, Outlet, useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

function LayOut() {

    return (
        <div>
            <Outlet/>
        </div>
    );
}

export default LayOut;