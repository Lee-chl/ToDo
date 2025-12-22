import React from "react";
import "./index.css";
import App from "./App";
import Login from "./Login";
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Box from '@mui/material/Box';
import {Typography} from "@mui/material";

function Copyright() {
    return (
        <Typography variant="body2" color="textSecondary" align="center">
            {"Copyright "}
            fsoftwareengineer , {new Date().getFullYear()}
            {"."}
        </Typography>
    );
}

class AppRouter extends React.Component {
    render() {
        return (
            <div>
                <Router>
                    <div>
                        <Routes>
                            <Route path="/login" element={<Login/>}/>
                            <Route path="/" element={<App/>}/>
                        </Routes>
                    </div>
                    <Box mt={5}>
                        <Copyright/>
                    </Box>
                </Router>
            </div>
        );
    }
}

export default AppRouter;