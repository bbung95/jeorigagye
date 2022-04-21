import React from "react";
import { Button, Container, Navbar, Nav } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

function Home (){
    return(
      <Container>
          <div className="row d-flex justify-content-center align-items-center h-100">
              <div className="col-md-4">
                  <h2>Home</h2>
              </div>
          </div>
      </Container>
    );
}

export default Home;