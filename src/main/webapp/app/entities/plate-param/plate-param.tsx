import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './plate-param.reducer';
import { IPlateParam } from 'app/shared/model/plate-param.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPlateParamProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const PlateParam = (props: IPlateParamProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { plateParamList, match, loading } = props;
  return (
    <div>
      <h2 id="plate-param-heading">
        Plate Params
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Plate Param
        </Link>
      </h2>
      <div className="table-responsive">
        {plateParamList && plateParamList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Value</th>
                <th>Peck Order</th>
                <th>Query Plate</th>
                <th>Cascader</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {plateParamList.map((plateParam, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${plateParam.id}`} color="link" size="sm">
                      {plateParam.id}
                    </Button>
                  </td>
                  <td>{plateParam.value}</td>
                  <td>{plateParam.peckOrder}</td>
                  <td>
                    {plateParam.queryPlateId ? <Link to={`query-plate/${plateParam.queryPlateId}`}>{plateParam.queryPlateId}</Link> : ''}
                  </td>
                  <td>
                    {plateParam.cascaderCascaderName ? (
                      <Link to={`cascader/${plateParam.cascaderId}`}>{plateParam.cascaderCascaderName}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${plateParam.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${plateParam.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${plateParam.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Plate Params found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ plateParam }: IRootState) => ({
  plateParamList: plateParam.entities,
  loading: plateParam.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PlateParam);
