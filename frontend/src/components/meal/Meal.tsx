import React from "react";
import {MenuItem} from "./Tagesplan";
import {Criteria} from "./Criteria";

type MealProps = {
    item: MenuItem
}

export class Meal extends React.Component<MealProps, {}> {
    formatEur(str: string) {
        const raw = str.split(" ")
        const value = raw[1];

        return Number(value).toFixed(2) + " €"
    }

    render() {
        return (
            <li key={this.props.item.name} style={{display: "block"}}>
                {this.props.item.name}
                <span style={{textTransform: "lowercase", display: "block"}}>
                    ({this.props.item.criteria.join(", ")})
                </span>
                <span style={{display: "block", textAlign: "right"}}>
                    {this.formatEur(this.props.item.price.STUD)}
                    <Criteria criteria={this.props.item.criteria}/>
                </span>
            </li>)
    }
}