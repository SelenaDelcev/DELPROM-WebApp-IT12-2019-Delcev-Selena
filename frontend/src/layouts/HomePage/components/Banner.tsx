import BannerZero from "../../../images/shimano.png";
import BannerOne from "../../../images/maxxis.png";
import BannerTwo from "../../../images/mucoff.png";
import '../../../App.css'

interface BannerImageProps {
    image: string;
    active?: boolean;
  }
  
  function BannerIncidator(props: { index: number; active?: boolean }) {
    return (
      <button
        type="button"
        data-bs-target="#bannerIndicators"
        data-bs-slide-to={props.index}
        className={props.active ? "active" : ""}
        aria-current={props.active}
      />
    );
  }
  
  function BannerImage(props: BannerImageProps) {
    return (
      <div
        className={"carousel-item " + (props.active ? "active" : "")}
        data-bs-interval="4000"
      >
        <div
          className="ratio"
          style={{
            ["--bs-aspect-ratio" as any]: "50%",
            maxHeight: "460px"
          }}
        >
          <img
            className="d-block w-100 h-100 bg-dark cover"
            alt=""
            src={props.image}
          />
        </div>
        <div className="carousel-caption d-none d-lg-block">
        </div>
      </div>
    );
  }
  
  export const Banner= () => {
    return (
      <div
        id="bannerIndicators"
        className="carousel slide"
        data-bs-ride="carousel"
        style={{ marginTop: "16px" }}
      >
        <div className="carousel-indicators">
          <BannerIncidator index={0} active={true} />
          <BannerIncidator index={1} />
          <BannerIncidator index={2} />
        </div>
        <div className="carousel-inner">
          <BannerImage image={BannerZero} active={true} />
          <BannerImage image={BannerOne} />
          <BannerImage image={BannerTwo} />
        </div>
      </div>
    );
  }