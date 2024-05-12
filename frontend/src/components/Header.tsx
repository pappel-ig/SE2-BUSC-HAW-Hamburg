import React from "react";
import headerImage from "../assets/mensamatch_logo.png";

export class Header extends React.Component {
    render() {return (
        <div className="header">
            <img src={headerImage} alt="Logo"/>
        </div>
    )}
}