import Bicycle from "../../../images/bicycle.png";
import Hemlet from "../../../images/helmet.png";
import Tool from "../../../images/tool.png";
import Service from "../../../images/service.png";
import '../../../App.css'

export const Category = () => {
    return (
        <div id='containerBackground' className="container">
            <div className="row">
                <div className="col-md-3">
                    <a href="#">
                        <img
                            src={Bicycle}
                            className="img-fluid rounded-circle"
                            alt="..."
                        />
                        <div className="text-center categoryName">BICIKLI</div>
                    </a>
                </div>
                <div className="col-md-3">
                    <a href="#">
                        <img
                            src={Hemlet}
                            className="img-fluid rounded-circle"
                            alt="..."
                        />
                        <div className="text-center categoryName">OPREMA</div>
                    </a>
                </div>
                <div className="col-md-3">
                    <a href="#">
                        <img
                            src={Tool}
                            className="img-fluid rounded-circle"
                            alt="..."
                        />
                        <div className="text-center categoryName">DELOVI</div>
                    </a>
                </div>
                <div className="col-md-3">
                    <a href="#">
                        <img
                            src={Service}
                            className="img-fluid rounded-circle"
                            alt="..."
                        />
                        <div className="text-center categoryName">SERVIS</div>
                    </a>
                </div>
            </div>
        </div>
    );
}