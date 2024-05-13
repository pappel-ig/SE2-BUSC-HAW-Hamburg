import profileImage from "../assets/mensamatch_profileImg.png";

const budget = 0.666; //Hier Budget
const filters: string[] = [];

/*Testfilter*/
filters.push("vegan");
filters.push("vegetarisch");
filters.push("enthält keine laktosehaltigen Lebensmittel");


function Profile() {

    const activeFilters = filters.join(", ")

    return (
        <>
            <div className="boxinbox2">
                <img src={profileImage} alt="Logo"/>
            </div>
            <div className="boxinbox2">
                <h1>Max Mustermann</h1>
                <br/>
                <h3>Budget</h3>
                <div className="budget-box">
                    <p>{budget}</p>
                </div>
                <br/>
                <h3>Aktive Filter</h3>
                <div className="boxinbox3">
                    <p>{activeFilters}</p>
                </div>
            </div>
        </>
    );
}

export default Profile;

