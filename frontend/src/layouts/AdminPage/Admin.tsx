import AddNewProduct from './components/AddNewProduct';

interface Props {
    isAdmin: boolean;
    accessToken: string;
}

export const Admin: React.FC<Props> = ({ isAdmin, accessToken }) => {

    return (
        <div className='adminPanel'>
            {isAdmin ? (
                <div className='container'>
                    <div className='mt-5'>
                        <h3>DELPROM prodavnica</h3>
                        <nav>
                            <div className='nav nav-tabs' id='nav-tab' role='tablist'>
                                <button className='nav-link active' id='nav-add-book-tab' data-bs-toggle='tab'
                                    data-bs-target='#nav-add-book' type='button' role='tab' aria-controls='nav-add-book'
                                    aria-selected='false'
                                >
                                    Dodaj nov proizvod
                                </button>
                                <button className='nav-link' id='nav-quantity-tab' data-bs-toggle='tab'
                                    data-bs-target='#nav-quantity' type='button' role='tab' aria-controls='nav-quantity'
                                    aria-selected='true'
                                >
                                    Pregled proizvoda
                                </button>
                                <button className='nav-link' id='nav-messages-tab' data-bs-toggle='tab'
                                    data-bs-target='#nav-messages' type='button' role='tab' aria-controls='nav-messages'
                                    aria-selected='false'
                                >
                                    Dodaj novog korisnika
                                </button>
                                <button className='nav-link' id='nav-quantity-tab' data-bs-toggle='tab'
                                    data-bs-target='#nav-quantity' type='button' role='tab' aria-controls='nav-quantity'
                                    aria-selected='true'
                                >
                                    Pregled korisnika
                                </button>
                                <button className='nav-link' id='nav-quantity-tab' data-bs-toggle='tab'
                                    data-bs-target='#nav-quantity' type='button' role='tab' aria-controls='nav-quantity'
                                    aria-selected='true'
                                >
                                    Pregled porudzbina
                                </button>
                            </div>
                        </nav>
                        <div className='tab-content' id='nav-tabContent'>
                            <div className='tab-pane fade show active' id='nav-add-book' role='tabpanel'
                                aria-labelledby='nav-add-book-tab'>
                                <AddNewProduct accessToken={accessToken} />
                            </div>
                            <div className='tab-pane fade' id='nav-quantity' role='tabpanel' aria-labelledby='nav-quantity-tab'>

                            </div>
                            <div className='tab-pane fade' id='nav-messages' role='tabpanel' aria-labelledby='nav-messages-tab'>

                            </div>
                        </div>
                    </div>
                </div>
            ) : (
                <div>
                    <h1>Zabranjen prikaz za korisnike koji nisu admini</h1>
                </div>
            )}

        </div >
    );
}