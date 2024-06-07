import React from "react";
import headerImage from "../assets/mensamatch_logo.png";
import {Link} from "react-router-dom";
import {User} from "./Frame";

type MenuProps = {
    user: User
}

export class Menu extends React.Component<MenuProps, {}> {

    showProfileOrLoginOptions() {
        if (this.props.user.loggedIn) return (
            <>
                <Link to={"/profile"}><li>Profile</li></Link>
                <Link to={"/recommend"}><li>Empfehlung</li></Link>
            </>
        )
        else return (
            <>
                <Link to={"/login"}><li>Login</li></Link>
                <Link to={"/register"}><li>Registrieren</li></Link>
            </>
        )
    }

    render() {return (
        <div className="menubox frame-item-menu">
            <nav role="navigation">
                <div id="menuToggle">
                    {/*<input type="checkbox"/>*/}
                    <span></span>
                    <span></span>
                    <span></span>
                    <ul id="menu">
                        <div>
                            <Link to={"/"}>
                                <li>Home</li>
                            </Link>
                            <Link to={"/menu"}>
                                <li>Tagesplan</li>
                            </Link>
                            {this.showProfileOrLoginOptions()}
                        </div>
                    </ul>
                </div>
            </nav>
        </div>
    )
    }
}