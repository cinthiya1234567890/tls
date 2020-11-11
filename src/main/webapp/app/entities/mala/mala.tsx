import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './mala.reducer';
import { IMala } from 'app/shared/model/mala.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMalaProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Mala = (props: IMalaProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { malaList, match, loading } = props;
  return (
    <div>
      <h2 id="mala-heading">
        Malas
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Mala
        </Link>
      </h2>
      <div className="table-responsive">
        {malaList && malaList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Mala Name</th>
                <th>Is Editable</th>
                <th>Description</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {malaList.map((mala, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${mala.id}`} color="link" size="sm">
                      {mala.id}
                    </Button>
                  </td>
                  <td>{mala.malaName}</td>
                  <td>{mala.isEditable ? 'true' : 'false'}</td>
                  <td>{mala.description}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${mala.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${mala.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${mala.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Malas found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ mala }: IRootState) => ({
  malaList: mala.entities,
  loading: mala.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Mala);
