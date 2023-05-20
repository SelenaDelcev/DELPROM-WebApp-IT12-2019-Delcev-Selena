import { Banner } from '../HomePage/components/Banner';
import { Category } from './components/Category';
import { Carousel } from './components/Carousel';

export const HomePage = () => {
    return (
        <div className="header-widget-region" role="complementary">
            <div className="col-full">
                <div id="text-30" className="widget widget_text">
                    <div className="textwidget"><p>BESPLATNA DOSTAVA ZA PORUDŽBINE KOJE PRELAZE <strong>9.000,00 rsd</strong></p></div>
                </div>
                <Banner/>
                <Category/>
                <Carousel />
            </div>
        </div>
    );
}