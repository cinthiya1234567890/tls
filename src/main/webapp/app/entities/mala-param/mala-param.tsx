import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './mala-param.reducer';
import { IMalaParam } from 'app/shared/model/mala-param.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMalaParamProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const MalaParam = (props: IMalaParamProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { malaParamList, match, loading } = props;
  return (
    <div>
      <h2 id="mala-param-heading">
        Mala Params
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Mala Param
        </Link>
      </h2>
      <div className="table-responsive">
        {malaParamList && malaParamList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Peck Order</th>
                <th>Mala</th>
                <th>Field</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {malaParamList.map((malaParam, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${malaParam.id}`} color="link" size="sm">
                      {malaParam.id}
                    </Button>
                  </td>
                  <td>{malaParam.peckOrder}</td>
                  <td>{malaParam.malaMalaName ? <Link to={`mala/${malaParam.malaId}`}>{malaParam.malaMalaName}</Link> : ''}</td>
                  <td>{malaParam.fieldFieldName ? <Link to={`field/${malaParam.fieldId}`}>{malaParam.fieldFieldName}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${malaParam.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${malaParam.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${malaParam.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Mala Params found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ malaParam }: IRootState) => ({
  malaParamList: malaParam.entities,
  loading: malaParam.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MalaParam);
