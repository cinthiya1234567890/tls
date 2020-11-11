import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './query-plate.reducer';
import { IQueryPlate } from 'app/shared/model/query-plate.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IQueryPlateDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const QueryPlateDetail = (props: IQueryPlateDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { queryPlateEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          QueryPlate [<b>{queryPlateEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="isRange">Is Range</span>
          </dt>
          <dd>{queryPlateEntity.isRange ? 'true' : 'false'}</dd>
          <dt>
            <span id="isMulti">Is Multi</span>
          </dt>
          <dd>{queryPlateEntity.isMulti ? 'true' : 'false'}</dd>
          <dt>
            <span id="peckOrder">Peck Order</span>
          </dt>
          <dd>{queryPlateEntity.peckOrder}</dd>
          <dt>Query</dt>
          <dd>{queryPlateEntity.queryQueryName ? queryPlateEntity.queryQueryName : ''}</dd>
          <dt>Field</dt>
          <dd>{queryPlateEntity.fieldFieldName ? queryPlateEntity.fieldFieldName : ''}</dd>
        </dl>
        <Button tag={Link} to="/query-plate" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/query-plate/${queryPlateEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ queryPlate }: IRootState) => ({
  queryPlateEntity: queryPlate.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(QueryPlateDetail);
