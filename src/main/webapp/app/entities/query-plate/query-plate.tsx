import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './query-plate.reducer';
import { IQueryPlate } from 'app/shared/model/query-plate.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IQueryPlateProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const QueryPlate = (props: IQueryPlateProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { queryPlateList, match, loading } = props;
  return (
    <div>
      <h2 id="query-plate-heading">
        Query Plates
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Query Plate
        </Link>
      </h2>
      <div className="table-responsive">
        {queryPlateList && queryPlateList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Is Range</th>
                <th>Is Multi</th>
                <th>Peck Order</th>
                <th>Query</th>
                <th>Field</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {queryPlateList.map((queryPlate, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${queryPlate.id}`} color="link" size="sm">
                      {queryPlate.id}
                    </Button>
                  </td>
                  <td>{queryPlate.isRange ? 'true' : 'false'}</td>
                  <td>{queryPlate.isMulti ? 'true' : 'false'}</td>
                  <td>{queryPlate.peckOrder}</td>
                  <td>{queryPlate.queryQueryName ? <Link to={`query/${queryPlate.queryId}`}>{queryPlate.queryQueryName}</Link> : ''}</td>
                  <td>{queryPlate.fieldFieldName ? <Link to={`field/${queryPlate.fieldId}`}>{queryPlate.fieldFieldName}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${queryPlate.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${queryPlate.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${queryPlate.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Query Plates found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ queryPlate }: IRootState) => ({
  queryPlateList: queryPlate.entities,
  loading: queryPlate.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(QueryPlate);
