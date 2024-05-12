import React from "react";
import {MenuItem} from "./Tagesplan";

type CriteriaProps = {
    criteria: string[]
}

export class Criteria extends React.Component<CriteriaProps, {}> {
    render() {
        return (
            <>
                {this.props.criteria.includes("Vegan") && (
                    <img src="vegan.png" alt="Vegan" style={{float: "right", marginLeft: "10px"}}/>
                )}
                {this.props.criteria.includes("Vegetarisch") &&
                    (<img src="vegetarisch.png" alt="Vegan" style={{float: "right", marginLeft: "10px",}}/>
                )}
                {this.props.criteria.includes("enthält keine laktosehaltigen Lebensmittel") &&
                    (<img src="laktosefrei.png" alt="Vegan" style={{float: "right", marginLeft: "10px"}}/>
                )}
            </>)
    }
}