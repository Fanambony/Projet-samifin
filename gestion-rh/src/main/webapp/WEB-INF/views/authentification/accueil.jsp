<%@include file="../utils/header.jsp" %>

<style>
    .circle-card {
        border-radius: 50%;
        width: 180px;
        height: 180px;
        margin: 0 auto;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 1.5rem;
    }
    .custom-modal .modal-dialog {
        margin-top: 0px;
    }
    /*.card {
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        border: none;
    }
    .card-body {
        display: flex;
        flex-direction: column;
        justify-content: center; 
        height: 100%;
    }
    .card-title {
        margin-bottom: 1rem;
    }*/
  </style>

<div class="container-fluid">
    <div class="row">
        <!-- Tableau de bord -->
        <div class="col-md-9 grid-margin stretch-card">
            <div class="card">
                <div class="card-body">
                  <div class="row mb-3">
                    <div class="col-md-6">
                        <button type="button" class="btn btn-outline-primary btn-fw btn-block" data-toggle="modal" data-target="#faireDemandeModal">Faire une demande de conge</button>
                    </div>
                    <div class="col-md-6">
                        <button type="button" class="btn btn-outline-primary btn-fw btn-block" data-toggle="modal" data-target="#historiqueModal">Historique de mes demandes</button>
                    </div>
                </div>

                    <h4 class="card-title">Gestion de conge</h4>
                    
                    <div class="table-responsive">
                        <table class="table table-striped table-borderless">
                            <thead>
                                <tr>
                                    <th>Date demande</th>
                                    <th>Type demande</th>
                                    <th>Date debut</th>
                                    <th>Date fin</th>
                                    <th>Nbr jours</th>
                                    <th>Etat</th>
                                    <th>Action</th>
                                </tr>  
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="font-weight-bold">15 Sep 2018</td>
                                    <td>Conge</td>
                                    <td>21 Sep 2018</td>
                                    <td>23 Sep 2018</td>
                                    <td class="font-weight-bold">2</td>
                                    <td class="font-weight-medium"><div class="badge badge-info">En attente</div></td>
                                    <td>
                                      <button type="button" class="btn btn-info btn-rounded btn-icon" data-toggle="modal" data-target="#userDetailModal">
                                          <i class="ti-eye"></i>
                                      </button>
                                      <button type="button" class="btn btn-success btn-rounded btn-icon">
                                          <i class="ti-pencil"></i>
                                      </button>
                                      <button type="button" class="btn btn-danger btn-rounded btn-icon">
                                          <i class="ti-trash"></i>
                                      </button>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="font-weight-bold">01 Jun 2018</td>
                                    <td>Absence</td>
                                    <td>13 Jun 2018</td>
                                    <td>16 Jun 2018</td>
                                    <td class="font-weight-bold">3</td>
                                    <td class="font-weight-medium"><div class="badge badge-success">Accepte</div></td>
                                </tr>
                                <tr>
                                    <td class="font-weight-bold">27 Sep 2018</td>
                                    <td>Absence</td>
                                    <td>28 Sep 2018</td>
                                    <td>30 Sep 2018 PM</td>
                                    <td class="font-weight-bold">1.5</td>
                                    <td class="font-weight-medium"><div class="badge badge-warning">A soumettre</div></td>
                                    <td>
                                      <button type="button" class="btn btn-info btn-rounded btn-icon" data-toggle="modal" data-target="#userDetailModal">
                                          <i class="ti-check"></i>
                                      </button>
                                      <button type="button" class="btn btn-success btn-rounded btn-icon">
                                          <i class="ti-pencil"></i>
                                      </button>
                                      <button type="button" class="btn btn-danger btn-rounded btn-icon">
                                          <i class="ti-trash"></i>
                                      </button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    
                </div>
            </div>
        </div>

        <!-- Cartes -->
        <div class="col-md-3 grid-margin stretch-card">
            <div class="row">
                <!-- Carte pour les conges restants -->
                <div class="col-md-12 mb-4">
                    <div class="card text-center" style="height: 300px;">
                        <div class="card-body">
                            <h4 class="card-title">Conge restant</h4>
                            <div class="circle-card" style="border: solid rgb(17, 142, 46) 20px;">
                                <h3>90 jours</h3>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- Carte pour les permissions restantes -->
                <div class="col-md-12 mb-4">
                    <div class="card text-center" style="height: 300px;">
                        <div class="card-body">
                            <h4 class="card-title">Permission restante</h4>
                            <div class="circle-card" style="border: solid rgb(231, 0, 0) 20px;">
                                <h3>06 jours</h3>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- modal demande conge -->
<div class="modal fade custom-modal" id="faireDemandeModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Demande de conge</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="faireDemandeForm">
                    <div class="container-fluid">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <div class="row">
                                    <label for="direction" class="col-sm-3 col-form-label">Type d'absence</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" id="direction" name="direction">
                                            <option value="">Conge annuel</option>
                                            <option value="">Autorisation d'absence</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-6">
                                <div class="form-group row">
                                    <label class="col-sm-3 col-form-label">Date debut</label>
                                    <div class="col-sm-8">
                                        <input type="date" class="form-control" name="date_debut" required>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group row">
                                    <label class="col-sm-3 col-form-label">Debut d'absence</label>
                                    <div class="col-sm-4">
                                        <div class="form-check">
                                            <label class="form-check-label">
                                            <input type="radio" class="form-check-input" name="membershipRadios" id="membershipRadios1" value="" checked>
                                            AM
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-sm-5">
                                        <div class="form-check">
                                            <label class="form-check-label">
                                                <input type="radio" class="form-check-input" name="membershipRadios" id="membershipRadios2" value="option2">
                                                PM
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-6">
                                <div class="form-group row">
                                    <label class="col-sm-3 col-form-label">Date fin</label>
                                    <div class="col-sm-8">
                                        <input type="date" class="form-control" name="date_fin" required>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group row">
                                    <label class="col-sm-3 col-form-label">Fin d'absence</label>
                                    <div class="col-sm-4">
                                        <div class="form-check">
                                            <label class="form-check-label">
                                            <input type="radio" class="form-check-input" name="membershipRadiosfin" id="membershipRadios3" value="">
                                            AM
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-sm-5">
                                        <div class="form-check">
                                            <label class="form-check-label">
                                                <input type="radio" class="form-check-input" name="membershipRadiosfin" id="membershipRadios4" value="option2" checked>
                                                PM
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="card-body">
                                <div class="form-group">
                                    <label for="exampleTextarea1">Commentaire</label>
                                    <textarea class="form-control" id="exampleTextarea1" rows="4"></textarea>
                                </div>
                            </div>
                        </div>

                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                <button type="button" class="btn btn-primary" onclick="submitForm()">Valider</button>
            </div>
        </div>
    </div>
</div>


<!-- modal historique -->
<div class="modal fade custom-modal" id="historiqueModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Historique demande</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="historiqueForm">
                    <div class="container-fluid">
                        <div class="table-responsive pt-3">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>First name</th>
                                        <th>Progress</th>
                                        <th>Amount</th>
                                        <th>Deadline</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>Herman Beck</td>
                                        <td>
                                            <div class="progress">
                                            <div class="progress-bar bg-success" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                        </td>
                                        <td>$ 77.99</td>
                                        <td>May 15, 2015</td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>Messsy Adam</td>
                                        <td>
                                            <div class="progress">
                                            <div class="progress-bar bg-danger" role="progressbar" style="width: 75%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                        </td>
                                        <td>$245.30</td>
                                        <td>July 1, 2015</td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td>John Richards</td>
                                        <td>
                                            <div class="progress">
                                            <div class="progress-bar bg-warning" role="progressbar" style="width: 90%" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                        </td>
                                        <td>$138.00</td>
                                        <td>Apr 12, 2015</td>
                                    </tr>
                                    <tr>
                                        <td>4</td>
                                        <td>Peter Meggik</td>
                                        <td>
                                            <div class="progress">
                                            <div class="progress-bar bg-primary" role="progressbar" style="width: 50%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                        </td>
                                        <td>$ 77.99</td>
                                        <td>May 15, 2015</td>
                                    </tr>
                                    <tr>
                                        <td>5</td>
                                        <td>Edward</td>
                                        <td>
                                            <div class="progress">
                                                <div class="progress-bar bg-danger" role="progressbar" style="width: 35%" aria-valuenow="35" aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                        </td>
                                        <td>$ 160.25</td>
                                        <td>May 03, 2015</td>
                                    </tr>
                                    <tr>
                                        <td>6</td>
                                        <td>John Doe</td>
                                        <td>
                                            <div class="progress">
                                                <div class="progress-bar bg-info" role="progressbar" style="width: 65%" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                        </td>
                                        <td>$ 123.21</td>
                                        <td>April 05, 2015</td>
                                    </tr>
                                    <tr>
                                        <td>7</td>
                                        <td>Henry Tom</td>
                                        <td>
                                            <div class="progress">
                                                <div class="progress-bar bg-warning" role="progressbar" style="width: 20%" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                        </td>
                                        <td>$ 150.00</td>
                                        <td>June 16, 2015</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                <button type="button" class="btn btn-primary" onclick="submitForm()">Valider</button>
            </div>
        </div>
    </div>
</div>

<%@include file="../utils/footer.jsp" %>
